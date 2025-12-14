param(
  [string]$OutDir = "${PSScriptRoot}\tablet10-screenshots"
)

$ErrorActionPreference = 'Stop'

Add-Type -AssemblyName System.Drawing

function New-Bitmap([int]$w, [int]$h) {
  $bmp = New-Object System.Drawing.Bitmap($w, $h, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
  $g = [System.Drawing.Graphics]::FromImage($bmp)
  $g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
  $g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
  $g.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality
  return @{ bmp = $bmp; g = $g }
}

function RoundedRectPath([int]$x, [int]$y, [int]$w, [int]$h, [int]$r) {
  $path = New-Object System.Drawing.Drawing2D.GraphicsPath
  $path.AddArc($x, $y, $r, $r, 180, 90)
  $path.AddArc($x + $w - $r, $y, $r, $r, 270, 90)
  $path.AddArc($x + $w - $r, $y + $h - $r, $r, $r, 0, 90)
  $path.AddArc($x, $y + $h - $r, $r, $r, 90, 90)
  $path.CloseFigure()
  return $path
}

function Draw-Background($g, $rect) {
  $bg = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
    $rect,
    [System.Drawing.Color]::FromArgb(255, 15, 23, 42),
    [System.Drawing.Color]::FromArgb(255, 37, 99, 235),
    0
  )
  $g.FillRectangle($bg, $rect)
  $bg.Dispose()

  $noise = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(14, 255, 255, 255))
  for ($i=0; $i -lt 220; $i++) {
    $x = Get-Random -Minimum 0 -Maximum $rect.Width
    $y = Get-Random -Minimum 0 -Maximum $rect.Height
    $s = Get-Random -Minimum 1 -Maximum 3
    $g.FillEllipse($noise, $x, $y, $s, $s)
  }
  $noise.Dispose()
}

function Draw-AppBar($g, [int]$w, [int]$barH, [int]$padX, [int]$titleY, [int]$dotY, [int]$dotSize, [int]$dotGap, [string]$title) {
  $bar = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(220, 17, 24, 39))
  $g.FillRectangle($bar, 0, 0, $w, $barH)
  $bar.Dispose()

  $font = New-Object System.Drawing.Font('Segoe UI', [int]($barH*0.45), [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(245, 255, 255, 255))
  $g.DrawString($title, $font, $brush, (New-Object System.Drawing.PointF([single]$padX, [single]$titleY)))
  $brush.Dispose(); $font.Dispose()

  $dot = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(180, 255, 255, 255))
  for ($i=0; $i -lt 3; $i++) { $g.FillEllipse($dot, ($w - ($padX + 3*($dotSize + $dotGap))) + ($i*($dotSize + $dotGap)), $dotY, $dotSize, $dotSize) }
  $dot.Dispose()
}

function Draw-Card($g, [int]$x, [int]$y, [int]$w, [int]$h, [int]$r) {
  $path = RoundedRectPath $x $y $w $h $r
  $fill = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(220, 255, 255, 255))
  $g.FillPath($fill, $path)
  $fill.Dispose()
  $stroke = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(45, 0, 0, 0), 2)
  $g.DrawPath($stroke, $path)
  $stroke.Dispose(); $path.Dispose()
}

function Draw-Avatar($g, [int]$x, [int]$y, [int]$size, [int]$strokeW) {
  $b = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 59, 130, 246))
  $g.FillEllipse($b, $x, $y, $size, $size)
  $b.Dispose()
  $pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(130, 255, 255, 255), $strokeW)
  $g.DrawEllipse($pen, $x, $y, $size, $size)
  $pen.Dispose()
}

function Draw-Text($g, [string]$text, [int]$x, [int]$y, [int]$size, [bool]$bold=$false, [int]$alpha=255) {
  $style = if ($bold) { [System.Drawing.FontStyle]::Bold } else { [System.Drawing.FontStyle]::Regular }
  $font = New-Object System.Drawing.Font('Segoe UI', $size, $style, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb($alpha, 17, 24, 39))
  $g.DrawString($text, $font, $brush, (New-Object System.Drawing.PointF([single]$x, [single]$y)))
  $brush.Dispose(); $font.Dispose()
}

function SavePng([System.Drawing.Bitmap]$bmp, [string]$path) {
  $bmp.Save($path, [System.Drawing.Imaging.ImageFormat]::Png)
  $bmp.Dispose()
}

# Target: 2560x1440 (16:9) - meets Play Console tablet 10" constraints
$w = 2560
$h = 1440
$scale = [double]$w / 1920.0

function S([double]$v) { return [int][math]::Round($v * $scale) }

New-Item -ItemType Directory -Force -Path $OutDir | Out-Null

$pad = S 70
$top = S 170
$barH = S 120
$r = S 26
$avatar = S 64
$avatarStroke = [math]::Max(3, (S 5))

# 01: Newsfeed (two columns)
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w $barH $pad (S 36) (S 22) (S 8) (S 14) 'Newsfeed'

$leftX = $pad
$rightX = S 1000
$cardW = S 850
$cardH = S 260

for ($i=0; $i -lt 3; $i++) {
  $y = $top + ($i * (S 290))

  Draw-Card $g $leftX $y $cardW $cardH $r
  Draw-Avatar $g ($leftX + (S 30)) ($y + (S 30)) $avatar $avatarStroke
  Draw-Text $g ('User ' + ($i+1)) ($leftX + (S 120)) ($y + (S 34)) (S 28) $true 255
  Draw-Text $g 'Just now - Public' ($leftX + (S 120)) ($y + (S 74)) (S 20) $false 160
  Draw-Text $g 'A quick update from my day...' ($leftX + (S 30)) ($y + (S 120)) (S 24) $false 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, ($leftX + (S 30)), ($y + (S 160)), ($cardW - (S 60)), (S 70))
  $imgBrush.Dispose()

  Draw-Card $g $rightX $y $cardW $cardH $r
  Draw-Avatar $g ($rightX + (S 30)) ($y + (S 30)) $avatar $avatarStroke
  Draw-Text $g ('User ' + ($i+4)) ($rightX + (S 120)) ($y + (S 34)) (S 28) $true 255
  Draw-Text $g '5m ago - Friends' ($rightX + (S 120)) ($y + (S 74)) (S 20) $false 160
  Draw-Text $g 'New photo in the album' ($rightX + (S 30)) ($y + (S 120)) (S 24) $false 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, ($rightX + (S 30)), ($y + (S 160)), ($cardW - (S 60)), (S 70))
  $imgBrush.Dispose()
}

$g.Dispose()
$p1 = Join-Path $OutDir 'tablet10-screenshot-01-newsfeed.png'
SavePng $bmp $p1

# 02: Comments (split view)
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w $barH $pad (S 36) (S 22) (S 8) (S 14) 'Comments'

Draw-Card $g $pad $top (S 1120) (S 820) $r
Draw-Avatar $g ($pad + (S 40)) ($top + (S 50)) (S 72) $avatarStroke
Draw-Text $g 'User 1' ($pad + (S 140)) ($top + (S 58)) (S 30) $true 255
Draw-Text $g 'Today - Public' ($pad + (S 140)) ($top + (S 100)) (S 20) $false 160
Draw-Text $g 'Great story from the weekend trip.' ($pad + (S 40)) ($top + (S 160)) (S 26) $false 220
$photo = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
$g.FillRectangle($photo, ($pad + (S 40)), ($top + (S 220)), (S 1040), (S 420))
$photo.Dispose()

Draw-Card $g (S 1230) $top (S 620) (S 820) $r
Draw-Text $g 'Thread' (S 1270) (S 210) (S 28) $true 220
$cy = S 270
for ($i=0; $i -lt 6; $i++) {
  Draw-Card $g (S 1260) $cy (S 560) (S 105) $r
  Draw-Avatar $g (S 1280) ($cy + (S 20)) (S 52) $avatarStroke
  Draw-Text $g ('User ' + ($i+2)) (S 1350) ($cy + (S 22)) (S 22) $true 230
  Draw-Text $g 'Nice!' (S 1350) ($cy + (S 54)) (S 20) $false 190
  $cy += (S 118)
}

$g.Dispose()
$p2 = Join-Path $OutDir 'tablet10-screenshot-02-comments.png'
SavePng $bmp $p2

# 03: Create post
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w $barH $pad (S 36) (S 22) (S 8) (S 14) 'Create post'

Draw-Card $g (S 240) (S 190) (S 1440) (S 740) $r
Draw-Text $g "What's on your mind?" (S 300) (S 260) (S 30) $false 210
$photo = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
$g.FillRectangle($photo, (S 300), (S 320), (S 1320), (S 470))
$photo.Dispose()
Draw-Text $g 'Add a photo' (S 870) (S 520) (S 28) $false 120

$btn = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 37, 99, 235))
$g.FillRectangle($btn, (S 820), (S 840), (S 280), (S 80))
$btn.Dispose()
Draw-Text $g 'POST' (S 905) (S 858) (S 30) $true 255

$g.Dispose()
$p3 = Join-Path $OutDir 'tablet10-screenshot-03-create-post.png'
SavePng $bmp $p3

# 04: Profile
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w $barH $pad (S 36) (S 22) (S 8) (S 14) 'Profile'

$banner = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(90, 255, 255, 255))
$g.FillRectangle($banner, $pad, $top, (S 1780), (S 220))
$banner.Dispose()

Draw-Avatar $g ($pad + (S 70)) ($top + (S 40)) (S 140) $avatarStroke
Draw-Text $g 'Newfeed User' ($pad + (S 250)) ($top + (S 55)) (S 36) $true 245
Draw-Text $g 'Posts 24   Followers 1.2k   Following 180' ($pad + (S 250)) ($top + (S 105)) (S 22) $false 200

Draw-Card $g $pad (S 420) (S 570) (S 570) $r
Draw-Text $g 'About' ($pad + (S 40)) (S 460) (S 28) $true 220
Draw-Text $g 'Bio: Sharing moments and stories.' ($pad + (S 40)) (S 520) (S 22) $false 190
Draw-Text $g 'Location: Viet Nam' ($pad + (S 40)) (S 560) (S 22) $false 190
Draw-Text $g 'Joined: 2025' ($pad + (S 40)) (S 600) (S 22) $false 190

Draw-Card $g (S 680) (S 420) (S 1170) (S 570) $r
Draw-Text $g 'Recent posts' (S 720) (S 460) (S 28) $true 220
for ($i=0; $i -lt 3; $i++) {
  $yy = (S 520) + ($i * (S 150))
  Draw-Card $g (S 720) $yy (S 1090) (S 130) $r
  Draw-Text $g ('Post ' + ($i+1)) (S 760) ($yy + (S 34)) (S 24) $true 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, (S 980), ($yy + (S 24)), (S 800), (S 84))
  $imgBrush.Dispose()
}

$g.Dispose()
$p4 = Join-Path $OutDir 'tablet10-screenshot-04-profile.png'
SavePng $bmp $p4

# 05: Notifications
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w $barH $pad (S 36) (S 22) (S 8) (S 14) 'Notifications'

Draw-Card $g (S 240) $top (S 1440) (S 820) $r
$ny = S 220
for ($i=0; $i -lt 7; $i++) {
  Draw-Card $g (S 290) $ny (S 1340) (S 95) $r
  Draw-Avatar $g (S 320) ($ny + (S 20)) (S 52) $avatarStroke
  Draw-Text $g ('User ' + ($i+1) + ' liked your post') (S 390) ($ny + (S 22)) (S 24) $false 210
  Draw-Text $g '2h ago' (S 1500) ($ny + (S 24)) (S 18) $false 150
  $ny += (S 110)
}

$g.Dispose()
$p5 = Join-Path $OutDir 'tablet10-screenshot-05-notifications.png'
SavePng $bmp $p5

# Report sizes
Get-ChildItem -Path $OutDir -Filter 'tablet10-screenshot-*.png' | Sort-Object Name | ForEach-Object {
  $img = [System.Drawing.Image]::FromFile($_.FullName)
  $len = $_.Length
  $iw = $img.Width
  $ih = $img.Height
  $img.Dispose()
  Write-Output ("FILE: $($_.Name)")
  Write-Output ("  Size: ${iw}x${ih} | Bytes: $len")
}

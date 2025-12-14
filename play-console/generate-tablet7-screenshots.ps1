param(
  [string]$OutDir = "${PSScriptRoot}\tablet7-screenshots"
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

function Draw-Background($g, $rect) {
  $bg = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
    $rect,
    [System.Drawing.Color]::FromArgb(255, 15, 23, 42),
    [System.Drawing.Color]::FromArgb(255, 37, 99, 235),
    0
  )
  $g.FillRectangle($bg, $rect)
  $bg.Dispose()

  $noise = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(18, 255, 255, 255))
  for ($i=0; $i -lt 140; $i++) {
    $x = Get-Random -Minimum 0 -Maximum $rect.Width
    $y = Get-Random -Minimum 0 -Maximum $rect.Height
    $s = Get-Random -Minimum 1 -Maximum 3
    $g.FillEllipse($noise, $x, $y, $s, $s)
  }
  $noise.Dispose()
}

function Draw-AppBar($g, [int]$w, [string]$title) {
  $bar = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(220, 17, 24, 39))
  $g.FillRectangle($bar, 0, 0, $w, 120)
  $bar.Dispose()

  $font = New-Object System.Drawing.Font('Segoe UI', 46, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(245, 255, 255, 255))
  $g.DrawString($title, $font, $brush, (New-Object System.Drawing.PointF([single]64, [single]36)))
  $brush.Dispose(); $font.Dispose()

  $dot = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(180, 255, 255, 255))
  for ($i=0; $i -lt 3; $i++) { $g.FillEllipse($dot, ($w-90) + ($i*22), 22, 8, 8) }
  $dot.Dispose()
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

function Draw-Card($g, [int]$x, [int]$y, [int]$w, [int]$h) {
  $path = RoundedRectPath $x $y $w $h 26
  $fill = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(220, 255, 255, 255))
  $g.FillPath($fill, $path)
  $fill.Dispose()
  $stroke = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(45, 0, 0, 0), 2)
  $g.DrawPath($stroke, $path)
  $stroke.Dispose(); $path.Dispose()
}

function Draw-Avatar($g, [int]$x, [int]$y, [int]$size) {
  $b = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 59, 130, 246))
  $g.FillEllipse($b, $x, $y, $size, $size)
  $b.Dispose()
  $pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(130, 255, 255, 255), 5)
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

$w = 1920
$h = 1080

New-Item -ItemType Directory -Force -Path $OutDir | Out-Null

# 01: Newsfeed (two columns)
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w 'Newsfeed'

$leftX = 70
$rightX = 1000
$topY = 170
$cardW = 850
$cardH = 260

for ($i=0; $i -lt 3; $i++) {
  $y = $topY + ($i*290)
  Draw-Card $g $leftX $y $cardW $cardH
  Draw-Avatar $g ($leftX+30) ($y+30) 64
  Draw-Text $g ('User ' + ($i+1)) ($leftX+120) ($y+34) 28 $true 255
  Draw-Text $g 'Just now - Public' ($leftX+120) ($y+74) 20 $false 160
  Draw-Text $g 'A quick update from my day...' ($leftX+30) ($y+120) 24 $false 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, ($leftX+30), ($y+160), ($cardW-60), 70)
  $imgBrush.Dispose()

  Draw-Card $g $rightX $y $cardW $cardH
  Draw-Avatar $g ($rightX+30) ($y+30) 64
  Draw-Text $g ('User ' + ($i+4)) ($rightX+120) ($y+34) 28 $true 255
  Draw-Text $g '5m ago - Friends' ($rightX+120) ($y+74) 20 $false 160
  Draw-Text $g 'New photo in the album' ($rightX+30) ($y+120) 24 $false 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, ($rightX+30), ($y+160), ($cardW-60), 70)
  $imgBrush.Dispose()
}

$g.Dispose()
$p1 = Join-Path $OutDir 'tablet7-screenshot-01-newsfeed.png'
SavePng $bmp $p1

# 02: Comments (split view)
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w 'Comments'

# Left: post
Draw-Card $g 70 170 1120 820
Draw-Avatar $g 110 220 72
Draw-Text $g 'User 1' 210 228 30 $true 255
Draw-Text $g 'Today - Public' 210 270 20 $false 160
Draw-Text $g 'Great story from the weekend trip.' 110 330 26 $false 220
$photo = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
$g.FillRectangle($photo, 110, 390, 1040, 420)
$photo.Dispose()

# Right: thread
Draw-Card $g 1230 170 620 820
Draw-Text $g 'Thread' 1270 210 28 $true 220
$cy = 270
for ($i=0; $i -lt 6; $i++) {
  Draw-Card $g 1260 $cy 560 105
  Draw-Avatar $g 1280 ($cy+20) 52
  Draw-Text $g ('User ' + ($i+2)) 1350 ($cy+22) 22 $true 230
  Draw-Text $g 'Nice!' 1350 ($cy+54) 20 $false 190
  $cy += 118
}

$g.Dispose()
$p2 = Join-Path $OutDir 'tablet7-screenshot-02-comments.png'
SavePng $bmp $p2

# 03: Create post
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w 'Create post'

Draw-Card $g 240 190 1440 740
Draw-Text $g "What's on your mind?" 300 260 30 $false 210
$photo = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
$g.FillRectangle($photo, 300, 320, 1320, 470)
$photo.Dispose()
Draw-Text $g 'Add a photo' 870 520 28 $false 120

$btn = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 37, 99, 235))
$g.FillRectangle($btn, 820, 840, 280, 80)
$btn.Dispose()
Draw-Text $g 'POST' 905 858 30 $true 255

$g.Dispose()
$p3 = Join-Path $OutDir 'tablet7-screenshot-03-create-post.png'
SavePng $bmp $p3

# 04: Profile
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w 'Profile'

$banner = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(90, 255, 255, 255))
$g.FillRectangle($banner, 70, 170, 1780, 220)
$banner.Dispose()

Draw-Avatar $g 140 210 140
Draw-Text $g 'Newfeed User' 320 225 36 $true 245
Draw-Text $g 'Posts 24   Followers 1.2k   Following 180' 320 275 22 $false 200

Draw-Card $g 70 420 570 570
Draw-Text $g 'About' 110 460 28 $true 220
Draw-Text $g 'Bio: Sharing moments and stories.' 110 520 22 $false 190
Draw-Text $g 'Location: Viet Nam' 110 560 22 $false 190
Draw-Text $g 'Joined: 2025' 110 600 22 $false 190

Draw-Card $g 680 420 1170 570
Draw-Text $g 'Recent posts' 720 460 28 $true 220
for ($i=0; $i -lt 3; $i++) {
  $y = 520 + ($i*150)
  Draw-Card $g 720 $y 1090 130
  Draw-Text $g ('Post ' + ($i+1)) 760 ($y+34) 24 $true 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, 980, ($y+24), 800, 84)
  $imgBrush.Dispose()
}

$g.Dispose()
$p4 = Join-Path $OutDir 'tablet7-screenshot-04-profile.png'
SavePng $bmp $p4

# 05: Notifications
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g $w 'Notifications'

Draw-Card $g 240 170 1440 820
$ny = 220
for ($i=0; $i -lt 7; $i++) {
  Draw-Card $g 290 $ny 1340 95
  Draw-Avatar $g 320 ($ny+20) 52
  Draw-Text $g ('User ' + ($i+1) + ' liked your post') 390 ($ny+22) 24 $false 210
  Draw-Text $g '2h ago' 1500 ($ny+24) 18 $false 150
  $ny += 110
}

$g.Dispose()
$p5 = Join-Path $OutDir 'tablet7-screenshot-05-notifications.png'
SavePng $bmp $p5

# Report sizes
Get-ChildItem -Path $OutDir -Filter 'tablet7-screenshot-*.png' | Sort-Object Name | ForEach-Object {
  $img = [System.Drawing.Image]::FromFile($_.FullName)
  $len = $_.Length
  $iw = $img.Width
  $ih = $img.Height
  $img.Dispose()
  Write-Output ("FILE: $($_.Name)")
  Write-Output ("  Size: ${iw}x${ih} | Bytes: $len")
}

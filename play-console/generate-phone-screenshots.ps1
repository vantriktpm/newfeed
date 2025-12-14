param(
  [string]$OutDir = "${PSScriptRoot}\phone-screenshots"
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

function Save-And-Report($bmp, [string]$path) {
  $bmp.Save($path, [System.Drawing.Imaging.ImageFormat]::Png)
  $bmp.Dispose()
  $img = [System.Drawing.Image]::FromFile($path)
  $len = (Get-Item $path).Length
  $img.Dispose()
  Write-Output ("OK: $path")
  Write-Output ("Size: ${($w)}x${($h)}")
  Write-Output ("Bytes: $len")
}

function Draw-Background($g, $rect) {
  $bg = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
    $rect,
    [System.Drawing.Color]::FromArgb(255, 15, 23, 42),
    [System.Drawing.Color]::FromArgb(255, 37, 99, 235),
    90
  )
  $g.FillRectangle($bg, $rect)
  $bg.Dispose()

  $overlay = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
    $rect,
    [System.Drawing.Color]::FromArgb(40, 255, 255, 255),
    [System.Drawing.Color]::FromArgb(0, 255, 255, 255),
    0
  )
  $g.FillRectangle($overlay, $rect)
  $overlay.Dispose()
}

function Draw-AppBar($g, [string]$title) {
  $bar = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(210, 17, 24, 39))
  $g.FillRectangle($bar, 0, 0, 1080, 160)
  $bar.Dispose()

  $font = New-Object System.Drawing.Font('Segoe UI', 52, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(245, 255, 255, 255))
  $g.DrawString($title, $font, $brush, (New-Object System.Drawing.PointF([single]72, [single]52)))
  $brush.Dispose()
  $font.Dispose()

  # simple status bar dots
  $dot = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(180, 255, 255, 255))
  for ($i=0; $i -lt 3; $i++) { $g.FillEllipse($dot, 980 + ($i*26), 26, 10, 10) }
  $dot.Dispose()
}

function Draw-Card($g, [int]$x, [int]$y, [int]$w, [int]$h) {
  $card = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(210, 255, 255, 255))

  # Rounded rect (manual)
  $r = 28
  $path = New-Object System.Drawing.Drawing2D.GraphicsPath
  $path.AddArc($x, $y, $r, $r, 180, 90)
  $path.AddArc($x + $w - $r, $y, $r, $r, 270, 90)
  $path.AddArc($x + $w - $r, $y + $h - $r, $r, $r, 0, 90)
  $path.AddArc($x, $y + $h - $r, $r, $r, 90, 90)
  $path.CloseFigure()
  $g.FillPath($card, $path)
  $card.Dispose()

  $stroke = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(45, 0, 0, 0), 2)
  $g.DrawPath($stroke, $path)
  $stroke.Dispose()
  $path.Dispose()
}

function Draw-Avatar($g, [int]$x, [int]$y, [int]$size) {
  $b = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 59, 130, 246))
  $g.FillEllipse($b, $x, $y, $size, $size)
  $b.Dispose()

  $pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(130, 255, 255, 255), 6)
  $g.DrawEllipse($pen, $x, $y, $size, $size)
  $pen.Dispose()
}

function Draw-TextLine($g, [string]$text, [int]$x, [int]$y, [int]$size, [int]$alpha=255) {
  $font = New-Object System.Drawing.Font('Segoe UI', $size, [System.Drawing.FontStyle]::Regular, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb($alpha, 17, 24, 39))
  $g.DrawString($text, $font, $brush, (New-Object System.Drawing.PointF([single]$x, [single]$y)))
  $brush.Dispose()
  $font.Dispose()
}

function Draw-CTA($g, [string]$text, [int]$x, [int]$y) {
  $btn = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 37, 99, 235))
  $g.FillRectangle($btn, $x, $y, 440, 110)
  $btn.Dispose()

  $font = New-Object System.Drawing.Font('Segoe UI', 42, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
  $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 255, 255, 255))
  $sf = New-Object System.Drawing.StringFormat
  $sf.Alignment = [System.Drawing.StringAlignment]::Center
  $sf.LineAlignment = [System.Drawing.StringAlignment]::Center
  $g.DrawString($text, $font, $brush, (New-Object System.Drawing.RectangleF([single]$x, [single]$y, [single]440, [single]110)), $sf)
  $sf.Dispose(); $brush.Dispose(); $font.Dispose()
}

$w = 1080
$h = 1920

New-Item -ItemType Directory -Force -Path $OutDir | Out-Null

# 01: Newsfeed
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g 'Newsfeed'

$y = 200
for ($i=0; $i -lt 3; $i++) {
  Draw-Card $g 54 $y 972 440
  Draw-Avatar $g 90 ($y+42) 86
  Draw-TextLine $g ('User ' + ($i+1)) 198 ($y+48) 36 255
  Draw-TextLine $g 'Just now - Public' 198 ($y+95) 26 160
  Draw-TextLine $g 'A quick update from my day...' 90 ($y+160) 34 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, 90, ($y+220), 880, 180)
  $imgBrush.Dispose()
  $y += 480
}

$g.Dispose()
$path1 = Join-Path $OutDir 'phone-screenshot-01-newsfeed.png'
$bmp.Save($path1, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# 02: Comments
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g 'Comments'

Draw-Card $g 54 210 972 420
Draw-Avatar $g 90 260 86
Draw-TextLine $g 'User 1' 198 268 36 255
Draw-TextLine $g 'Great post! Thanks for sharing.' 90 350 34 220

$cy = 680
for ($i=0; $i -lt 5; $i++) {
  Draw-Card $g 54 $cy 972 200
  Draw-Avatar $g 90 ($cy+52) 70
  Draw-TextLine $g ('User ' + ($i+2)) 182 ($cy+56) 32 255
  Draw-TextLine $g 'Nice!' 182 ($cy+104) 30 200
  $cy += 230
}

$input = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(235, 255, 255, 255))
$g.FillRectangle($input, 0, 1740, 1080, 180)
$input.Dispose()
Draw-TextLine $g 'Write a comment...' 72 1795 34 140

$g.Dispose()
$path2 = Join-Path $OutDir 'phone-screenshot-02-comments.png'
$bmp.Save($path2, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# 03: Create post
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g 'Create post'

Draw-Card $g 54 240 972 980
Draw-TextLine $g "What's on your mind?" 90 310 38 210

$photo = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
$g.FillRectangle($photo, 90, 420, 880, 520)
$photo.Dispose()
Draw-TextLine $g 'Add a photo' 410 660 36 120

Draw-CTA $g 'POST' 320 1300

$g.Dispose()
$path3 = Join-Path $OutDir 'phone-screenshot-03-create-post.png'
$bmp.Save($path3, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# 04: Profile
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g 'Profile'

$banner = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(120, 255, 255, 255))
$g.FillRectangle($banner, 0, 160, 1080, 330)
$banner.Dispose()

Draw-Avatar $g 420 360 240
$fontName = New-Object System.Drawing.Font('Segoe UI', 56, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$brushName = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(245, 255, 255, 255))
$sf = New-Object System.Drawing.StringFormat
$sf.Alignment = [System.Drawing.StringAlignment]::Center
$sf.LineAlignment = [System.Drawing.StringAlignment]::Center
$g.DrawString('Newfeed User', $fontName, $brushName, (New-Object System.Drawing.RectangleF([single]0, [single]620, [single]1080, [single]80)), $sf)
$sf.Dispose(); $brushName.Dispose(); $fontName.Dispose()

Draw-Card $g 54 760 972 200
Draw-TextLine $g 'Posts: 24' 130 820 34 220
Draw-TextLine $g 'Followers: 1.2k' 400 820 34 220
Draw-TextLine $g 'Following: 180' 720 820 34 220

$py = 1010
for ($i=0; $i -lt 2; $i++) {
  Draw-Card $g 54 $py 972 400
  Draw-TextLine $g 'Recent post' 90 ($py+60) 34 220
  $imgBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 226, 232, 240))
  $g.FillRectangle($imgBrush, 90, ($py+120), 880, 220)
  $imgBrush.Dispose()
  $py += 440
}

$g.Dispose()
$path4 = Join-Path $OutDir 'phone-screenshot-04-profile.png'
$bmp.Save($path4, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# 05: Notifications
$ctx = New-Bitmap $w $h
$bmp = $ctx.bmp; $g = $ctx.g
$rect = New-Object System.Drawing.Rectangle(0,0,$w,$h)
Draw-Background $g $rect
Draw-AppBar $g 'Notifications'

$ny = 220
for ($i=0; $i -lt 7; $i++) {
  Draw-Card $g 54 $ny 972 170
  Draw-Avatar $g 90 ($ny+45) 70
  Draw-TextLine $g ('User ' + ($i+1) + ' liked your post') 182 ($ny+52) 32 220
  Draw-TextLine $g '2h ago' 182 ($ny+104) 26 150
  $ny += 200
}

$g.Dispose()
$path5 = Join-Path $OutDir 'phone-screenshot-05-notifications.png'
$bmp.Save($path5, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Report sizes
Get-ChildItem -Path $OutDir -Filter 'phone-screenshot-*.png' | Sort-Object Name | ForEach-Object {
  $img = [System.Drawing.Image]::FromFile($_.FullName)
  $len = $_.Length
  $iw = $img.Width
  $ih = $img.Height
  $img.Dispose()
  Write-Output ("FILE: $($_.Name)")
  Write-Output ("  Size: ${iw}x${ih} | Bytes: $len")
}

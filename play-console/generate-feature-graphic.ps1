param(
  [string]$OutPath = "${PSScriptRoot}\feature-graphic-1024x500.png"
)

$ErrorActionPreference = 'Stop'

Add-Type -AssemblyName System.Drawing

$w = 1024
$h = 500

$bmp = New-Object System.Drawing.Bitmap($w, $h, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$g = [System.Drawing.Graphics]::FromImage($bmp)
$g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
$g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
$g.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality

$rect = New-Object System.Drawing.Rectangle(0, 0, $w, $h)

# Background gradient
$bg = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
  $rect,
  [System.Drawing.Color]::FromArgb(255, 17, 24, 39),
  [System.Drawing.Color]::FromArgb(255, 37, 99, 235),
  0
)
$g.FillRectangle($bg, $rect)
$bg.Dispose()

# Subtle diagonal highlight
$path = New-Object System.Drawing.Drawing2D.GraphicsPath
$path.AddPolygon(@(
  (New-Object System.Drawing.Point(0, 0)),
  (New-Object System.Drawing.Point([int]($w * 0.62), 0)),
  (New-Object System.Drawing.Point([int]($w * 0.42), $h)),
  (New-Object System.Drawing.Point(0, $h))
))
$hl = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(28, 255, 255, 255))
$g.FillPath($hl, $path)
$hl.Dispose()
$path.Dispose()

# Left logo circle + N
$circleX = 80
$circleY = 90
$circleSize = 320

$circleBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(36, 255, 255, 255))
$g.FillEllipse($circleBrush, $circleX, $circleY, $circleSize, $circleSize)
$circleBrush.Dispose()

$circlePen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(130, 255, 255, 255), 8)
$g.DrawEllipse($circlePen, $circleX, $circleY, $circleSize, $circleSize)
$circlePen.Dispose()

$fontN = New-Object System.Drawing.Font('Segoe UI', 170, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$sf = New-Object System.Drawing.StringFormat
$sf.Alignment = [System.Drawing.StringAlignment]::Center
$sf.LineAlignment = [System.Drawing.StringAlignment]::Center

$nRectShadow = New-Object System.Drawing.RectangleF([single]$circleX, [single]($circleY + 20), [single]$circleSize, [single]$circleSize)
$nRect = New-Object System.Drawing.RectangleF([single]$circleX, [single]$circleY, [single]$circleSize, [single]$circleSize)

$shadow = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(90, 0, 0, 0))
$fg = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 255, 255, 255))
$g.DrawString('N', $fontN, $shadow, $nRectShadow, $sf)
$g.DrawString('N', $fontN, $fg, $nRect, $sf)
$shadow.Dispose()
$fg.Dispose()
$fontN.Dispose()

# Title/subtitle on right
$titleFont = New-Object System.Drawing.Font('Segoe UI', 84, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$subFont = New-Object System.Drawing.Font('Segoe UI', 34, [System.Drawing.FontStyle]::Regular, [System.Drawing.GraphicsUnit]::Pixel)

$titleBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(245, 255, 255, 255))
$subBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(200, 255, 255, 255))

$rightX = 450
$g.DrawString('Newfeed', $titleFont, $titleBrush, (New-Object System.Drawing.PointF([single]$rightX, [single]160)))
$g.DrawString('Share - Like - Comment', $subFont, $subBrush, (New-Object System.Drawing.PointF([single]$rightX, [single]270)))

$titleBrush.Dispose()
$subBrush.Dispose()
$titleFont.Dispose()
$subFont.Dispose()
$sf.Dispose()

# Save
$g.Dispose()
$bmp.Save($OutPath, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

$img = [System.Drawing.Image]::FromFile($OutPath)
$len = (Get-Item $OutPath).Length

Write-Output ("OK: $OutPath")
Write-Output ("Size: $($img.Width)x$($img.Height)")
Write-Output ("Bytes: $len")

$img.Dispose()

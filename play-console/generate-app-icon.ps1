param(
  [string]$OutPath = "${PSScriptRoot}\app-icon-512.png"
)

$ErrorActionPreference = 'Stop'

Add-Type -AssemblyName System.Drawing

$w = 512
$h = 512

$bmp = New-Object System.Drawing.Bitmap($w, $h, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$g = [System.Drawing.Graphics]::FromImage($bmp)
$g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
$g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
$g.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality

$rect = New-Object System.Drawing.Rectangle(0, 0, $w, $h)
$bg = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
  $rect,
  [System.Drawing.Color]::FromArgb(255, 31, 41, 55),
  [System.Drawing.Color]::FromArgb(255, 59, 130, 246),
  45
)
$g.FillRectangle($bg, $rect)
$bg.Dispose()

$overlay = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(36, 255, 255, 255))
$g.FillEllipse($overlay, 64, 64, 384, 384)
$overlay.Dispose()

$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(160, 255, 255, 255), 10)
$g.DrawEllipse($pen, 64, 64, 384, 384)
$pen.Dispose()

$font = New-Object System.Drawing.Font('Segoe UI', 210, [System.Drawing.FontStyle]::Bold, [System.Drawing.GraphicsUnit]::Pixel)
$sf = New-Object System.Drawing.StringFormat
$sf.Alignment = [System.Drawing.StringAlignment]::Center
$sf.LineAlignment = [System.Drawing.StringAlignment]::Center

$shadow = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(90, 0, 0, 0))
$fg = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 255, 255, 255))

$g.DrawString('N', $font, $shadow, (New-Object System.Drawing.RectangleF(0, 18, $w, $h)), $sf)
$g.DrawString('N', $font, $fg, (New-Object System.Drawing.RectangleF(0, 0, $w, $h)), $sf)

$shadow.Dispose()
$fg.Dispose()
$sf.Dispose()
$font.Dispose()

$g.Dispose()
$bmp.Save($OutPath, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

$img = [System.Drawing.Image]::FromFile($OutPath)
$len = (Get-Item $OutPath).Length

Write-Output ("OK: $OutPath")
Write-Output ("Size: $($img.Width)x$($img.Height)")
Write-Output ("Bytes: $len")

$img.Dispose()

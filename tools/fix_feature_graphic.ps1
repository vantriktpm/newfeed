param(
  [string]$Path = "play-console/feature-graphic-1024x500.png"
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

Add-Type -AssemblyName System.Drawing

if (-not (Test-Path -LiteralPath $Path)) {
  throw "File not found: $Path"
}

# Load into a writable bitmap without locking the file
$img = [System.Drawing.Bitmap]::FromFile($Path)
$bmp = New-Object System.Drawing.Bitmap $img
$img.Dispose()

$g = [System.Drawing.Graphics]::FromImage($bmp)
$g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
$g.TextRenderingHint = [System.Drawing.Text.TextRenderingHint]::AntiAliasGridFit

try {
  # Tagline area (under the title on the right)
  # Tight rectangle to avoid covering the title text
  $x = 430
  $y = 252
  $w = 610
  $h = 95

  # Clamp region to image bounds
  $x0 = [Math]::Max(0, $x)
  $y0 = [Math]::Max(0, $y)
  $x1 = [Math]::Min($bmp.Width - 1, $x + $w)
  $y1 = [Math]::Min($bmp.Height - 1, $y + $h)
  $rw = ($x1 - $x0)
  $rh = ($y1 - $y0)

  # Restore background by copying pixels from a clean area below (avoids a flat-color banner).
  # Works because the graphic background is essentially the same vertically.
  $offset = 150
  for ($yy = $y0; $yy -le $y1; $yy++) {
    $srcY = $yy + $offset
    if ($srcY -ge $bmp.Height) { $srcY = $yy - $offset }
    for ($xx = $x0; $xx -le $x1; $xx++) {
      $bmp.SetPixel($xx, $yy, $bmp.GetPixel($xx, $srcY))
    }
  }

  # Draw corrected tagline (replace broken encoding â€¢ with bullet •)
  $bullet = [char]0x2022
  $text = "Share $bullet Like $bullet Comment"
  $font = New-Object System.Drawing.Font "Segoe UI", 30, ([System.Drawing.FontStyle]::Regular), ([System.Drawing.GraphicsUnit]::Pixel)
  $fg = [System.Drawing.Color]::FromArgb(255, 245, 248, 255)
  $brushFg = New-Object System.Drawing.SolidBrush $fg

  $size = $g.MeasureString($text, $font)
  $tx = [int]($x0 + ($rw - $size.Width) / 2)
  $ty = [int]($y0 + ($rh - $size.Height) / 2)
  $g.DrawString($text, $font, $brushFg, $tx, $ty)

  $brushFg.Dispose()
  $font.Dispose()
}
finally {
  $g.Dispose()
}

$bmp.Save($Path, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

Write-Host "Updated: $Path"



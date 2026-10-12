package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = ElectricViolet,                   // #6C63FF (Electric Violet)
  onPrimary = Color.White,                    // #FFFFFF (High contrast on violet)
  primaryContainer = ElectricVioletContainer, // #E0DEFF
  onPrimaryContainer = MidnightNavy,          // #151A35 (> 13:1 contrast)
  secondary = MidnightNavy,                   // #151A35 (Midnight Navy)
  onSecondary = Color.White,                  // #FFFFFF (16.1:1 AAA contrast)
  secondaryContainer = MidnightNavyContainerLight,
  onSecondaryContainer = OnMidnightNavyContainerLight,
  tertiary = ElectricVioletLight,
  onTertiary = Color.White,
  tertiaryContainer = ElectricVioletContainer,
  onTertiaryContainer = OnElectricVioletContainer,
  background = BackgroundLight,               // #FFFFFF
  onBackground = TextPrimaryLight,            // #151A35
  surface = SurfaceLight,                     // #FFFFFF
  onSurface = TextPrimaryLight,               // #151A35
  surfaceVariant = SurfaceVariantLight,
  onSurfaceVariant = TextSecondaryLight,
  outline = OutlineLight,
  outlineVariant = OutlineVariantLight,
  error = ErrorColor,                         // #B3261E
  onError = Color.White,
  errorContainer = ErrorContainerLight,
  onErrorContainer = OnErrorContainerLight
)

private val DarkColorScheme = darkColorScheme(
  primary = ElectricVioletLight,              // #8E87FF (Lighter violet for AAA dark contrast)
  onPrimary = MidnightNavy,                   // #151A35 (9.8:1 AAA contrast)
  primaryContainer = Color(0xFF4338CA),
  onPrimaryContainer = ElectricVioletContainer,
  secondary = Color(0xFFBAC1EC),              // Soft Navy accent for dark mode readability
  onSecondary = MidnightNavy,
  secondaryContainer = MidnightNavyContainerDark,
  onSecondaryContainer = OnMidnightNavyContainerDark,
  tertiary = ElectricViolet,
  onTertiary = Color.White,
  tertiaryContainer = Color(0xFF3730A3),
  onTertiaryContainer = ElectricVioletContainer,
  background = BackgroundDark,               // #0F1120
  onBackground = TextPrimaryDark,            // #EDEFFF
  surface = SurfaceDark,                     // #1A1D2E
  onSurface = TextPrimaryDark,               // #EDEFFF
  surfaceVariant = SurfaceVariantDark,
  onSurfaceVariant = TextSecondaryDark,
  outline = OutlineDark,
  outlineVariant = OutlineVariantDark,
  error = ErrorDark,
  onError = Color(0xFF601410),
  errorContainer = ErrorContainerDark,
  onErrorContainer = OnErrorContainerDark
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve brand Midnight Navy & Electric Violet
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

package apps.tictactoe.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
  content: @Composable () -> Unit
) {

  ProvideAppColors(LightColorPalette) {
    MaterialTheme(
      colors = debugColors(false),
      typography = Typography,
      shapes = Shapes,
      content = content
    )
  }
}

@Composable
fun ProvideAppColors(
  colors: AppColors,
  content: @Composable () -> Unit
) {
  val colorPalette = remember {
    // Explicitly creating a new object here so we don't mutate the initial [colors]
    // provided, and overwrite the values set in it.
    colors.copy()
  }
  colorPalette.update(colors)
  CompositionLocalProvider(LocalAppColors provides colorPalette, content = content)
}

private val LocalAppColors = staticCompositionLocalOf<AppColors> {
  error("No LocalAppColors provided")
}

private val LightColorPalette = AppColors(
  primary = Design.orangeColor,
  primaryVariant = Design.defaultColor,
  secondary = Design.boardColor,
  secondaryVariant = Design.cellBorderColor,
  background = Design.backgroundColor,
  surface = Design.disabledOrangeColor,
  error = Design.defaultColor,
  onPrimary = Design.whiteColor,
  onSecondary = Design.defaultColor,
  onBackground = Design.defaultColor,
  onSurface = Design.defaultColor,
  onError = Design.defaultColor,
)

fun debugColors(
  darkTheme: Boolean,
  debugColor: Color = Color.Magenta
) = Colors(
  primary = debugColor,
  primaryVariant = debugColor,
  secondary = debugColor,
  secondaryVariant = debugColor,
  background = debugColor,
  surface = debugColor,
  error = debugColor,
  onPrimary = debugColor,
  onSecondary = debugColor,
  onBackground = debugColor,
  onSurface = debugColor,
  onError = debugColor,
  isLight = !darkTheme
)

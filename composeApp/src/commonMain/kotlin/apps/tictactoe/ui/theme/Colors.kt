package apps.tictactoe.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

object Design {
  val backgroundColor = Color(0xFFE53935) // A deep red color background
  val boardColor = Color(0xFFD9AB8A) // A light brown color for the board
  val cellBorderColor = Color(0xFF9e3901) // A darker brown for the cell border
  val textColor = Color.White // For any text that may appear over the deep red background
  val scoreBackgroundColor = Color(0xFFFFCDD2) // A lighter red for the score background
  val buttonColor = Color(0xFFFFAB91) // A light orange for buttons like "New Game"
  val whiteColor = Color(0xFFFFFFFF) // White color
  val orangeColor = Color(0xFFed6507) // A light orange for buttons like "New Game"
  val darkGreyColor = Color(0xFF4C4C4C) //
  val lightGreyColor = Color(0xFF666666) //
  val disabledOrangeColor = Color(0xFFd3a284)
  val defaultColor = Color.White
  // Define additional colors if needed for other elements like the top bar buttons
}

// If using Jetpack Compose, define corresponding color values for use in composables
// TODO Add in material theme
@Composable
fun getColors() = object {
  val backgroundColor = Color(0xFFE53935)
  val boardColor = Color(0xFFD7CCC8)
  val cellBorderColor = Color(0xFFA1887F)
  val textColor = Color.White
  val scoreBackgroundColor = Color(0xFFFFCDD2)
  val buttonColor = Color(0xFFFFAB91)

}

@Stable
class AppColors(
  primary: Color,
  primaryVariant: Color,
  secondary: Color,
  secondaryVariant: Color,
  background: Color,
  surface: Color,
  error: Color,
  onPrimary: Color,
  onSecondary: Color,
  onBackground: Color,
  onSurface: Color,
  onError: Color,
) {

  var primary by mutableStateOf(primary)
    private set
  var primaryVariant by mutableStateOf(primaryVariant)
    private set
  var secondary by mutableStateOf(secondary)
    private set
  var secondaryVariant by mutableStateOf(secondaryVariant)
    private set
  var background by mutableStateOf(background)
    private set
  var surface by mutableStateOf(surface)
    private set
  var error by mutableStateOf(error)
    private set
  var onPrimary by mutableStateOf(onPrimary)
    private set
  var onSecondary by mutableStateOf(onSecondary)
    private set
  var onBackground by mutableStateOf(onBackground)
    private set
  var onSurface by mutableStateOf(onSurface)
    private set
  var onError by mutableStateOf(onError)
    private set

  fun copy(): AppColors = AppColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
  )

  fun update(other: AppColors) {
    primary = other.primary
    primaryVariant = other.primaryVariant
    secondary = other.secondary
    secondaryVariant = other.secondaryVariant
    background = other.background
    surface = other.surface
    error = other.error
    onPrimary = other.onPrimary
    onSecondary = other.onSecondary
    onBackground = other.onBackground
    onSurface = other.onSurface
    onError = other.onError
  }
}

package apps.tictactoe.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Design {
  val backgroundColor = Color(0xFFE53935) // A deep red color background
  val boardColor = Color(0xFFD7CCC8) // A light brown color for the board
  val cellBorderColor = Color(0xFFA1887F) // A darker brown for the cell border
  val textColor = Color.White // For any text that may appear over the deep red background
  val scoreBackgroundColor = Color(0xFFFFCDD2) // A lighter red for the score background
  val buttonColor = Color(0xFFFFAB91) // A light orange for buttons like "New Game"
  // Define additional colors if needed for other elements like the top bar buttons
}

// If using Jetpack Compose, define corresponding color values for use in composables
@Composable
fun getColors() = object {
  val backgroundColor = Color(0xFFE53935)
  val boardColor = Color(0xFFD7CCC8)
  val cellBorderColor = Color(0xFFA1887F)
  val textColor = Color.White
  val scoreBackgroundColor = Color(0xFFFFCDD2)
  val buttonColor = Color(0xFFFFAB91)
}

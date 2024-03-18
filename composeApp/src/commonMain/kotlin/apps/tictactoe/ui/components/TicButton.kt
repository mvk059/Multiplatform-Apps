package apps.tictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.ui.theme.Design

@Composable
fun TicButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  textColor: Color = Design.whiteColor,
  buttonColor: Color = Design.orangeColor
) {

  Button(
    modifier = modifier
      .height(height = 48.dp)
      .defaultMinSize(minWidth = 32.dp)
      .padding(horizontal = 8.dp, /*vertical = 16.dp*/)
      .wrapContentWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
    onClick = onClick,
    content = {
      Text(
        modifier = Modifier,
        text = text,
        color = textColor,
        fontSize = 16.sp
      )
    }
  )
}

//@Preview
//@Composable
//private fun TicButtonPreview() {
//  MaterialTheme {
//    TicButton(text = "TicTacToe", onClick = {})
//  }
//}

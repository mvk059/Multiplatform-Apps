package apps.tictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.ui.theme.Design

@Composable
fun TicButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  enabled: Boolean = true,
  greyOut: Boolean = true,
  textColor: Color = Design.whiteColor,
  buttonColor: Color = Design.orangeColor,
  textStyle: TextStyle = MaterialTheme.typography.body1,
) {

  Button(
    modifier = modifier
      .height(height = 48.dp)
      .defaultMinSize(minWidth = 32.dp)
      .padding(horizontal = 8.dp, /*vertical = 16.dp*/)
      .wrapContentWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = if (greyOut) buttonColor else Design.disabledOrangeColor),
    onClick = onClick,
    enabled = enabled,
    content = {
      Text(
        modifier = Modifier,
        text = text,
        color = if (greyOut) textColor else Design.lightGreyColor,
//        fontSize = 16.sp,
        style = textStyle,
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

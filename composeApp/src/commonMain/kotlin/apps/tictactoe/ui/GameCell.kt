package apps.tictactoe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.Symbol

@Composable
fun GameCell(symbol: Symbol?, onClick: () -> Unit) {

  Box(
    modifier = Modifier
      .size(100.dp)
      .padding(8.dp)
      .border(2.dp, Color(0xFF795548))
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = symbol.toString() ?: "",
      fontSize = 32.sp,
      fontWeight = FontWeight.Bold,
      color = Color.White,
//      color = when (symbol) {
//        Symbol.X -> Color.Red
//        Symbol.O -> Color.Blue
//        else -> Color.Black
//      }
    )
  }
}
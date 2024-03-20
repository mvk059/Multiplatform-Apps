package apps.tictactoe.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.Cell
import apps.tictactoe.ui.theme.Design

@Composable
fun GameCell(
  cell: Cell,
  onClick: (Cell) -> Unit,
) {

  val symbol = cell.symbol?.value ?: ""

  Box(
    modifier = Modifier
      .size(100.dp)
      .padding(8.dp)
      .background(color = Design.cellBorderColor, shape = RoundedCornerShape(24.dp))
      .clickable(onClick = { onClick(cell) }),
    contentAlignment = Alignment.Center,
  ) {

    Text(
      text = symbol,
      fontSize = 32.sp,
      fontWeight = FontWeight.Bold,
      color = Color.White,
    )
  }
}
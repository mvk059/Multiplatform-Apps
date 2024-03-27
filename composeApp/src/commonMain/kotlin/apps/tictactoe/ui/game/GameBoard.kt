package apps.tictactoe.ui.game

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

@Composable
fun GameBoard(
  modifier: Modifier = Modifier,
  board: Board,
  onCellClicked: (Cell) -> Unit,
) {

  Column(
    modifier = modifier.wrapContentSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    content = {

      for (row in 0 until board.size) {
        Row(
          content = {
            for (col in 0 until board.size) {
              GameCell(
                cell = board.board[row][col],
                onClick = onCellClicked,
              )
            }
          }
        )
      }
    }
  )
}

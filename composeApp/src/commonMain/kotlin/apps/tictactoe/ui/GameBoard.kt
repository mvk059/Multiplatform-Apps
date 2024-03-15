package apps.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.tictactoe.logic.GameManager

@Composable
fun GameBoard(gameManager: GameManager, boardSize: Int = 3) {

  val gameState = remember { mutableStateOf(gameManager.getBoardState()) }

  Column(
    modifier = Modifier
      .background(Color(0xFFA1887F)) // Background color for the board
      .padding(16.dp),
    content = {

      for (row in 0 until boardSize) {
        Row(
          content = {
            for (col in 0 until boardSize) {
              GameCell(
                symbol = gameState.value[row][col].symbol,
                onClick = { gameManager.onCellClicked(row, col) }
              )
            }
          }
        )
      }
    }
  )
}

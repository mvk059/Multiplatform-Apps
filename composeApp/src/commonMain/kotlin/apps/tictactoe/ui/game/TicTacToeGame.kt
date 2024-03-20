package apps.tictactoe.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Game
import apps.tictactoe.ui.intro.GameViewModel
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeGame(
  vm: GameViewModel,
  game: Game,
  onCellClicked: (Cell) -> Unit,
) {

  Box(
    modifier = Modifier
      .wrapContentSize()
      .background(color = Design.boardColor, shape = RoundedCornerShape(16.dp)),
    content = {

      Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
          GameBoard(
            board = game.gameBoard,
            onCellClicked = onCellClicked,
          )
        }
      )
    }
  )
}

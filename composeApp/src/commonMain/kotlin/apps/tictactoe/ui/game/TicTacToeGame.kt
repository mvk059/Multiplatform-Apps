package apps.tictactoe.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Game
import apps.tictactoe.data.enums.GameWinStatus
import apps.tictactoe.ui.intro.GameViewModel
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeGame(
  game: Game,
  onCellClicked: (Cell) -> Unit,
) {

  Box(
    modifier = Modifier.wrapContentSize(),
    content = {

      Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {

          GameBoard(
            modifier = Modifier.background(color = Design.boardColor, shape = RoundedCornerShape(16.dp)),
            board = game.gameBoard,
            onCellClicked = onCellClicked,
          )

          Spacer(Modifier.height(16.dp))

          if (game.gameWinStatus != GameWinStatus.IN_PROGRESS) {
            Text(
              text = game.gameWinStatus.name,
              color = Design.whiteColor,
              fontSize = 16.sp,
            )
          }
        }
      )
    }
  )
}

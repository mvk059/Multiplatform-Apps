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
import apps.tictactoe.ui.components.TicButton
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeGame(
  game: Game,
  playerStatus: Pair<String, String>,
  onCellClicked: (Cell) -> Unit,
  shouldEnableUndo: () -> Boolean,
  onUndo: () -> Unit,
  onRestartGame: () -> Unit,
) {

  Box(
    modifier = Modifier.wrapContentSize(),
    content = {

      Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {

          Spacer(Modifier.height(16.dp))

          GameBoard(
            modifier = Modifier.background(color = Design.boardColor, shape = RoundedCornerShape(16.dp)),
            board = game.gameBoard,
            onCellClicked = onCellClicked,
          )

          Spacer(Modifier.height(16.dp))

          Text(
            text = "${playerStatus.first} ${playerStatus.second}",
            color = Design.whiteColor,
            fontSize = 16.sp,
          )

          Spacer(Modifier.height(16.dp))

          Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            content = {
              if (shouldEnableUndo()) {
                TicButton(
                  text = "Undo",
                  onClick = onUndo,
                )
              }

              Spacer(Modifier.width(16.dp))

              TicButton(
                text = "Restart Game",
                onClick = onRestartGame
              )

            }
          )

        }
      )
    }
  )
}

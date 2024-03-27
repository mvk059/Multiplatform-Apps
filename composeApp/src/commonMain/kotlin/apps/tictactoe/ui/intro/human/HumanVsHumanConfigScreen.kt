package apps.tictactoe.ui.intro.human

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.Game
import apps.tictactoe.ui.components.DropdownSelector
import apps.tictactoe.ui.theme.Design
import utils.WindowWidthSize

@Composable
fun HumanVsHumanConfigScreen(
  modifier: Modifier = Modifier,
  windowWidthSize: WindowWidthSize,
  numberOfPlayers: Int,
  onNumberOfPlayersUpdate: (Int) -> Unit,
) {

  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
    content = {

      Text(
        text = "Players",
        modifier = Modifier.padding(top = 8.dp),
        color = Design.whiteColor,
        fontSize = 32.sp,
      )

      DropdownSelector(
        label = "Number of players",
        items = (Game.MIN_PLAYERS..Game.MAX_PLAYERS).toList(),
        selectedItem = numberOfPlayers,
        onItemSelected = {
          onNumberOfPlayersUpdate(it)
        }
      )
    }
  )

}

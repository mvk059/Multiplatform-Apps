package apps.tictactoe.ui.intro.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.Symbol.Companion.getSymbol
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.ui.components.DropdownSelector

@Composable
fun PlayerConfigRow(
  modifier: Modifier = Modifier,
  playerConfig: PlayerConfig,
  playerIndex: Int,
  symbolsAvailable: List<Symbol>,
  onConfigUpdated: (PlayerConfig) -> Unit
) {

  var playerName by remember { mutableStateOf(playerConfig.name) }
  var selectedSymbol by remember { mutableStateOf(playerConfig.symbol) }

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    content = {

      TextField(
        value = playerName,
        onValueChange = { newName ->
          playerName = newName
          onConfigUpdated(playerConfig.copy(name = newName))
        },
        label = { Text("Name") }
      )

      Spacer(Modifier.width(8.dp))

      DropdownSelector(
        label = "Symbol for Player ${playerIndex + 1}",
        items = symbolsAvailable.map { it.value },
        selectedItem = playerConfig.symbol.value,
        onItemSelected = { newSymbol ->
          val symbol = newSymbol.getSymbol()!!
          selectedSymbol = symbol
          onConfigUpdated(playerConfig.copy(symbol = symbol))
        }
      )
    }
  )
}

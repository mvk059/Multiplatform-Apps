package apps.tictactoe.ui.intro.player

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig

@Composable
fun PlayersInfo(
  modifier: Modifier = Modifier,
  playersConfigs: List<PlayerConfig>,
  symbolsAvailable: List<Symbol>,
  onPlayerConfigUpdate: (Int, PlayerConfig) -> Unit,
) {

  Column(
    modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    content = {

      playersConfigs.forEachIndexed { index, playerConfig ->

        Box(
          modifier = Modifier
            .align(Alignment.CenterHorizontally) // Align the dropdown start to the box's start
            .zIndex(1f),
          content = {

            PlayerConfigRow(
              playerConfig = playerConfig,
              playerIndex = index,
              symbolsAvailable = symbolsAvailable,
              onConfigUpdated = { updatedConfig ->
                println("PlayerInfo new config: $updatedConfig")
                onPlayerConfigUpdate(index, updatedConfig)
              }
            )

            Spacer(Modifier.height(height = 8.dp))
          }
        )
      }
    }
  )

}

package apps.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.Game
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.config.GameConfigurator
import apps.tictactoe.logic.config.GameConfiguratorImpl
import apps.tictactoe.logic.playmode.AIDifficulty
import apps.tictactoe.logic.playmode.PlayMode
import apps.tictactoe.ui.intro.GameIntroViewModel
import apps.tictactoe.ui.intro.PlayerSelection
import apps.tictactoe.ui.intro.PlayersInfo
import apps.tictactoe.ui.intro.computer.HumanVsComputerConfigScreen
import apps.tictactoe.ui.intro.human.HumanVsHumanConfigScreen
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeIntro() {

  val gameConfigurator: GameConfigurator = GameConfiguratorImpl()
  val vm = remember { GameIntroViewModel(gameConfigurator) }
  val game = vm.game.value
  var playMode by remember { mutableStateOf(PlayMode.UNSELECTED) }
  var aiDifficulty by remember { mutableStateOf(AIDifficulty.UNSELECTED) }
//  val playersConfigs = remember { mutableStateListOf<PlayerConfig>() }

  println("Main start: $game")

  LaunchedEffect(playMode) {
    when(playMode) {
      PlayMode.HUMAN_VS_HUMAN -> vm.initializeForHumanPlayers(game.numberOfPlayers)
      PlayMode.HUMAN_VS_COMPUTER -> {} //TODO()
      PlayMode.UNSELECTED -> {} //TODO()
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().background(Design.backgroundColor),
    horizontalAlignment = Alignment.CenterHorizontally,
    content = {

      Text(
        text = "TicTacToe",
        modifier = Modifier.padding(16.dp),
        color = Design.textColor,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
      )

      Box(
        modifier = Modifier
          .padding(16.dp)
          .align(Alignment.CenterHorizontally),
        content = {

          Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {

              PlayerSelection(onPlayModeSelected = { playMode = it })

              Spacer(Modifier.height(16.dp))

              when (playMode) {

                PlayMode.HUMAN_VS_HUMAN -> {

                  HumanVsHumanConfigScreen(
                    numberOfPlayers = game.numberOfPlayers,
                    onNumberOfPlayersUpdate = vm::updatePlayers
                  )

                  Spacer(Modifier.height(16.dp))

                  PlayersInfo(
                    playersConfigs = game.players,
                    symbolsAvailable = vm.getAvailableSymbols(), //Symbol.getAllSymbols(),
                    onPlayerConfigUpdate = vm::updatePlayerConfig
                  )
                }

                PlayMode.HUMAN_VS_COMPUTER -> HumanVsComputerConfigScreen(gameConfigurator = gameConfigurator)
                else -> {}
              }
            }
          )
        }
      )
    }
  )
}
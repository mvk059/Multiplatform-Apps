package apps.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.logic.config.GameConfigurator
import apps.tictactoe.logic.config.GameConfiguratorImpl
import apps.tictactoe.data.enums.AIDifficulty
import apps.tictactoe.data.enums.PlayMode
import apps.tictactoe.ui.components.TicButton
import apps.tictactoe.ui.intro.GameIntroViewModel
import apps.tictactoe.ui.intro.computer.HumanVsComputerConfigScreen
import apps.tictactoe.ui.intro.human.HumanVsHumanConfigScreen
import apps.tictactoe.ui.intro.player.PlayerSelection
import apps.tictactoe.ui.intro.player.PlayersInfo
import apps.tictactoe.ui.intro.winconditions.WinConditions
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeIntro() {

  val gameConfigurator: GameConfigurator = GameConfiguratorImpl()
  val vm = remember { GameIntroViewModel(gameConfigurator) }
  val game = vm.game.value
  var playMode by remember { mutableStateOf(PlayMode.UNSELECTED) }
  var aiDifficulty by remember { mutableStateOf(AIDifficulty.UNSELECTED) }
  var startGame by remember { mutableStateOf(false) }
//  val playersConfigs = remember { mutableStateListOf<PlayerConfig>() }

  println("Main start: $game")

  LaunchedEffect(playMode) {
    when (playMode) {
      PlayMode.HUMAN_VS_HUMAN -> vm.initializeForHumanPlayers(game.numberOfPlayers)
      PlayMode.HUMAN_VS_COMPUTER -> {} //TODO()
      PlayMode.UNSELECTED -> {} //TODO()
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Design.backgroundColor)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
    content = {

      Text(
        text = "TicTacToe",
        modifier = Modifier.padding(16.dp),
        color = Design.textColor,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.h1
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

              Spacer(Modifier.height(32.dp))

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

                  Spacer(Modifier.height(16.dp))

                  WinConditions(
                    winConditions = game.winConditions,
                    onWinConditionUpdate = vm::onWinConditionUpdate
                  )
                }

                PlayMode.HUMAN_VS_COMPUTER -> HumanVsComputerConfigScreen(gameConfigurator = gameConfigurator)
                else -> {}
              }

              Spacer(Modifier.height(16.dp))

              val isEnabled = vm.validateIntroSetup()
              TicButton(
                text = "PLAY",
                onClick = {  },
                textStyle = MaterialTheme.typography.body1,
                greyOut = isEnabled,
              )

            }
          )
        }
      )
    }
  )
}

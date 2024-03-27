package apps.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.data.enums.AIDifficulty
import apps.tictactoe.data.enums.GameStatus
import apps.tictactoe.data.enums.PlayMode
import apps.tictactoe.logic.config.GameConfigurator
import apps.tictactoe.logic.config.GameConfiguratorImpl
import apps.tictactoe.logic.manager.GameManager
import apps.tictactoe.logic.manager.TicTacToeGameManager
import apps.tictactoe.ui.components.TicButton
import apps.tictactoe.ui.game.TicTacToeGame
import apps.tictactoe.ui.intro.GameViewModel
import apps.tictactoe.ui.intro.computer.HumanVsComputerConfigScreen
import apps.tictactoe.ui.intro.human.HumanVsHumanConfigScreen
import apps.tictactoe.ui.intro.player.PlayerSelection
import apps.tictactoe.ui.intro.player.PlayersInfo
import apps.tictactoe.ui.intro.winconditions.WinConditions
import apps.tictactoe.ui.theme.Design
import utils.WindowWidthSize
import utils.mapToWindowWidthSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TicTacToeIntro() {

  val windowSizeClass = calculateWindowSizeClass()

  // Getting the window size
  val windowWidthSize: MutableState<WindowWidthSize> = remember { mutableStateOf(WindowWidthSize.Compact) }

  val gameConfigurator: GameConfigurator = GameConfiguratorImpl()
  val gameManager: GameManager = TicTacToeGameManager()
  val vm = remember { GameViewModel(gameConfigurator, gameManager) }
  val game = vm.game.value
  var playMode by remember { mutableStateOf(PlayMode.UNSELECTED) }
  var aiDifficulty by remember { mutableStateOf(AIDifficulty.UNSELECTED) }

  println("Main start: $game")

  LaunchedEffect(playMode) {
    when (playMode) {
      PlayMode.HUMAN_VS_HUMAN -> vm.initializeForHumanPlayers(game.numberOfPlayers)
      PlayMode.HUMAN_VS_COMPUTER -> {} //TODO()
      PlayMode.UNSELECTED -> {} //TODO()
    }
  }

  windowWidthSize.value = windowSizeClass.mapToWindowWidthSize()

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .background(Design.backgroundColor),
    content = {

      Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
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

          println("Window Size Expanded: ${windowSizeClass.widthSizeClass}, ${windowWidthSize.value}")
//            println("Wide")
          Box(
            modifier = Modifier
              .padding(16.dp)
              .align(Alignment.CenterHorizontally),
            content = {

              when (game.gameStatus) {

                GameStatus.INTRO -> {
                  Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {

                      PlayerSelection(
                        windowWidthSize = windowWidthSize.value,
                        onPlayModeSelected = { playMode = it }
                      )

                      Spacer(Modifier.height(32.dp))

                      when (playMode) {

                        PlayMode.HUMAN_VS_HUMAN -> {

                          HumanVsHumanConfigScreen(
                            windowWidthSize = windowWidthSize.value,
                            numberOfPlayers = game.numberOfPlayers,
                            onNumberOfPlayersUpdate = vm::updatePlayers
                          )

                          Spacer(Modifier.height(16.dp))

                          PlayersInfo(
                            playersConfigs = game.players,
                            symbolsAvailable = vm.getAvailableSymbols(),
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
                        onClick = { vm.startGame() },
                        textStyle = MaterialTheme.typography.body1,
                        greyOut = isEnabled,
                      )

                    }
                  )
                }

                GameStatus.STARTED -> {
                  Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                      TicTacToeGame(
                        game = game,
                        onCellClicked = vm::makeMove
                      )
                    }
                  )
                }

                GameStatus.FINISHED -> {}
              }

            }
          )


        }
      )
    }
  )

}

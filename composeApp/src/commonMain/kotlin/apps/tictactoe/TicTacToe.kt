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
import apps.tictactoe.ui.intro.PlayerSelection
import apps.tictactoe.ui.intro.PlayersInfo
import apps.tictactoe.ui.intro.computer.HumanVsComputerConfigScreen
import apps.tictactoe.ui.intro.human.HumanVsHumanConfigScreen
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeIntro() {

//  val gameConfigurator: GameConfigurator by remember {  mutableStateOf(GameConfiguratorImpl()) }
  val gameConfigurator: GameConfigurator = GameConfiguratorImpl()
  var game: Game by remember { mutableStateOf(Game.init()) }
  var playMode by remember { mutableStateOf(PlayMode.UNSELECTED) }
//  var numberOfPlayers by remember { mutableStateOf(2) } // Default to 2 for Human vs Human
  var aiDifficulty by remember { mutableStateOf(AIDifficulty.UNSELECTED) }
//  val playersConfigs = remember { mutableStateListOf<PlayerConfig>() }

  LaunchedEffect(key1 = true) {
    println("Main init start")
    Symbol.initSymbols()
    game = gameConfigurator.initializeForHumanPlayers(game, game.numberOfPlayers)
    println("Main init end")
//    playersConfigs.addAll(gameConfigurator.initializeForHumanPlayers(numberOfPlayers))
  }

  println("Main start: $game")

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
                    onNumberOfPlayersUpdate = {

//                      numberOfPlayers = it
//                      gameConfigurator
                      game = gameConfigurator.updatePlayers(game, it)
//                      playersConfigs.addAll(gameConfigurator.initializeForHumanPlayers(it))
                    }
                  )

                  Spacer(Modifier.height(16.dp))

                  PlayersInfo(
                    playersConfigs = game.players,
                    symbolsAvailable = Symbol.getAllSymbols(),
                    onPlayerConfigUpdate = { index, config ->
                      println("Main config update : $index, $config")
                      game = gameConfigurator.updatePlayerConfig(game, index, config)
                    }
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
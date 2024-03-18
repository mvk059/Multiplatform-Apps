package apps.tictactoe.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import apps.tictactoe.logic.manager.GameManager
import apps.tictactoe.logic.manager.TicTacToeGameManager
import apps.tictactoe.ui.theme.Design

@Composable
fun TicTacToeGame() {

  val gameManager: GameManager = TicTacToeGameManager()
//  gameManager.initializeGame(
//    players = listOf(HumanPlayer(PlayerConfig("Manu", Symbol())), ComputerPlayer("Comp", Symbol(), EasyAI())),
//    boardSize = 3,
//    winConditions = listOf(HorizontalWin())
//  )

  Box(modifier = Modifier.background(Design.boardColor)) {
    GameBoard(gameManager, 3)
  }

//  Scaffold(
//    topBar = { TicTacToeTopBar() },
//    content = { TicTacToeContent(gameManager) },
//    bottomBar = { NewGameButton({}) }
//  )
}

@Composable
fun TicTacToeTopBar() {
  TopAppBar(
    title = { Text("Tic Tac Toe") },
    actions = {
      TextButton(onClick = { /* Handle Docs */ }) { Text("Docs") }
      TextButton(onClick = { /* Handle Play */ }) { Text("Play") }
      TextButton(onClick = { /* Handle About Us */ }) { Text("About Us") }
    }
  )
}

@Composable
fun TicTacToeContent(gameManager: GameManager) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
//    ScoreBoard(player1Score = 0, player2Score = 0)
    GameBoard(gameManager, 3)
  }
}

@Composable
fun NewGameButton(onClick: () -> Unit) {
  Button(onClick = onClick) {
    Text("New Game")
  }
}

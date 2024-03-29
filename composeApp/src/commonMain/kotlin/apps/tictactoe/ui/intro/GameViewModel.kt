package apps.tictactoe.ui.intro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Game
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.enums.GameStatus
import apps.tictactoe.data.enums.GameWinStatus
import apps.tictactoe.data.enums.WinCondition
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.config.GameConfigurator
import apps.tictactoe.logic.manager.GameManager

class GameViewModel(
  private val gameConfigurator: GameConfigurator,
  private val gameManager: GameManager,
) {

  // Holds the current game configuration
  var game: MutableState<Game> = mutableStateOf(Game.init())
    private set

  init {
    Symbol.initSymbols()
  }

  fun initializeForHumanPlayers(numberOfPlayers: Int) {
    val playersInfo = gameConfigurator.initializeForHumanPlayers(numberOfPlayers)
    game.value = game.value.copy(
      players = playersInfo,
      numberOfPlayers = numberOfPlayers,
      vsComputer = false,
      currentPlayerIndex = 0,
    )
  }

  fun updatePlayers(numberOfPlayers: Int) {
    val playersInfo = gameConfigurator.updatePlayers(numberOfPlayers, game.value.players)
    game.value = game.value.copy(
      players = playersInfo,
      numberOfPlayers = numberOfPlayers,
    )
  }

  fun updatePlayerConfig(index: Int, playerConfig: PlayerConfig) {
    val playersInfo = gameConfigurator.updatePlayerConfig(index, playerConfig, game.value.players)
    game.value = game.value.copy(
      players = playersInfo,
    )
  }

  fun onWinConditionUpdate(condition: WinCondition) {
    val currentConditions = game.value.winConditions.toMutableSet()
    if (currentConditions.contains(condition)) currentConditions.remove(condition)
    else currentConditions.add(condition)

    game.value = game.value.copy(
      winConditions = currentConditions.toSet()
    )
  }

  fun getAvailableSymbols(): List<Symbol> {
    val usedSymbols = game.value.players.map { it.symbol }
    return Symbol.getAllSymbols().filter { it !in usedSymbols }
  }

  fun validateIntroSetup(): Boolean {
    if (game.value.players.isEmpty()) return false
    val playerName = game.value.players.all { it.name.isNotEmpty() } // game.value.players.map { it.name.isNotBlank() }.isNotEmpty()
    val playerSymbol = game.value.players.all { it.symbol.value.isNotEmpty() } // game.value.players.map { it.symbol.value.isNotEmpty() }.isNotEmpty()
    val playerWinningConditions = game.value.winConditions.isNotEmpty()
    return playerName && playerSymbol && playerWinningConditions
  }

  fun startGame() {
    game.value = game.value.copy(
      currentPlayer = game.value.players[0],
      currentPlayerIndex = 0,
      gameStatus = GameStatus.STARTED,
      gameBoard = Board(game.value.numberOfPlayers + 1),
      moveHistory = mutableListOf()
    )
  }

  fun makeMove(cell: Cell) {
    if (game.value.gameWinStatus != GameWinStatus.IN_PROGRESS) return

    var currentPlayer = game.value.currentPlayer
    var currentPlayerIndex = game.value.currentPlayerIndex
    require(currentPlayer != null) { "Player is not part of game" }

    val isPlaced = gameManager.makeMove(currentPlayer, game.value.gameBoard, cell)
    if (isPlaced) {
      val moveHistory = game.value.moveHistory.toMutableList()
      moveHistory.add(currentPlayer to cell)
      val gameWinStatus = gameManager.checkForWin(game.value.gameBoard, cell, game.value.winConditions)
      println("Game ${gameWinStatus.name}")
      currentPlayerIndex = (currentPlayerIndex + 1) % game.value.players.size
      currentPlayer = game.value.players[currentPlayerIndex]
      game.value = game.value.copy(
        currentPlayerIndex = if (gameWinStatus == GameWinStatus.WIN) game.value.currentPlayerIndex else currentPlayerIndex,
        currentPlayer = if (gameWinStatus == GameWinStatus.WIN) game.value.currentPlayer else currentPlayer,
        moveHistory = moveHistory,
        gameWinStatus = gameWinStatus,
      )
    } else {
      // TODO Handle
    }
  }

  fun shouldShowUndo(): Boolean {
    if (game.value.gameWinStatus != GameWinStatus.IN_PROGRESS) return false
    return !game.value.gameBoard.isEmpty() // gameManager.isBoardEmpty(game.value.gameBoard)
  }

  fun undo() {
    println("Undo")
    val moveHistory = game.value.moveHistory.toMutableList()
    val (player, cell) = moveHistory.removeAt(moveHistory.lastIndex)
    game.value.gameBoard.clearCell(cell)
    println("Move undone. It's ${player.name}'s turn again.")
    println("CurrentPlayerIndex: ${game.value.currentPlayerIndex}, players: ${game.value.players}")

    val currentPlayerIndex = (game.value.currentPlayerIndex - 1 + game.value.players.size) % game.value.players.size
    val currentPlayer = game.value.players[currentPlayerIndex]
    game.value = game.value.copy(
      currentPlayerIndex = currentPlayerIndex,
      currentPlayer = currentPlayer,
      moveHistory = moveHistory,
    )
  }

  fun getPlayerStatus(): Pair<String, String> =
    when (game.value.gameWinStatus) {
      GameWinStatus.WIN -> Pair(game.value.currentPlayer?.name ?: "", game.value.gameWinStatus.name)
      GameWinStatus.DRAW -> Pair("", GameWinStatus.DRAW.name)
      GameWinStatus.IN_PROGRESS -> Pair("", "")
    }

  fun onRestartGame() {
    val moveHistory = game.value.moveHistory.toMutableList()
    game.value.gameBoard.resetBoard()
    moveHistory.clear()
    game.value = game.value.copy(
      moveHistory = moveHistory,
      currentPlayerIndex = 0,
      currentPlayer = game.value.players[0],
      gameStatus = GameStatus.STARTED,
      gameWinStatus = GameWinStatus.IN_PROGRESS,
    )
  }

}

package apps.tictactoe.logic

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.player.Player
import apps.tictactoe.logic.winconditions.WinCondition

class TicTacToeGameManager : GameManager {
    private lateinit var gameBoard: Board
    private lateinit var players: List<Player>
    private var currentPlayerIndex = 0
    private lateinit var winConditions: List<WinCondition>
    private val moveHistory = mutableListOf<Pair<Player, Cell>>()

    override fun initializeGame(players: List<Player>, boardSize: Int, winConditions: List<WinCondition>) {
        if (players.size < 2) throw IllegalArgumentException("At least two players are required.")
        this.players = players
        this.gameBoard = Board(boardSize)
        this.winConditions = winConditions
        currentPlayerIndex = 0
        moveHistory.clear()
    }

    override fun startGame() {
        // The game loop could be managed here if this were a console application.
        // For GUI applications, moves might be triggered by user interactions directly.
        println("Game started with ${players.size} players on a ${gameBoard.size}x${gameBoard.size} board.")
    }

    override fun makeMove(player: Player, cell: Cell) {
        require(players.contains(player)) { "Player not part of this game." }
        require(!cell.isOccupied) { "Cell is already occupied." }

        gameBoard.placeSymbol(cell, player.symbol)
        moveHistory.add(player to cell)
        checkForWin(player, cell)

        // Prepare for next player's turn
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    private fun checkForWin(player: Player, cell: Cell) {
        if (winConditions.any { it.checkWin(gameBoard, player.symbol, cell) }) {
            println("Player ${player.name} wins!")
            resetGame()
        } else if (gameBoard.isFull()) {
            println("The game is a draw.")
            resetGame()
        }
    }

    override fun undoMove() {
        if (moveHistory.isEmpty()) return

        val (player, cell) = moveHistory.removeAt(moveHistory.lastIndex)
        gameBoard.clearCell(cell)
        println("Move undone. It's ${player.name}'s turn again.")

        // Adjust the current player index to give the turn back to the player who had their move undone
        currentPlayerIndex = if (currentPlayerIndex - 1 < 0) players.lastIndex else currentPlayerIndex - 1
    }

    override fun resetGame() {
        gameBoard.resetBoard()
        moveHistory.clear()
        println("Game has been reset.")
    }

    override fun onCellClicked(row: Int, col: Int) {

    }

    override fun getBoardState(): Array<Array<Cell>> {
        return gameBoard.board
    }
}

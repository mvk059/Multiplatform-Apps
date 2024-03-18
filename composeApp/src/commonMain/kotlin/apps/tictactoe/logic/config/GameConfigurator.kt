package apps.tictactoe.logic.config

import apps.tictactoe.data.Game
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.computer.GameAI

interface GameConfigurator {
//    fun initializeForHumanPlayers(numberOfPlayers: Int): List<PlayerConfig>
    fun initializeForHumanPlayers(game: Game, numberOfPlayers: Int): Game
//    fun initializeForComputerPlayer(playerConfig: PlayerConfig, difficulty: GameAI)
    fun updatePlayers(game: Game, numberOfPlayers: Int): Game
    fun updatePlayerConfig(game: Game, index: Int, playerConfig: PlayerConfig): Game
}
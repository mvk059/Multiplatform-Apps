package apps.tictactoe.logic.config

import apps.tictactoe.data.player.PlayerConfig

interface GameConfigurator {
    fun initializeForHumanPlayers(numberOfPlayers: Int): List<PlayerConfig>

    fun updatePlayers(numberOfPlayers: Int, playersInfo: List<PlayerConfig>): List<PlayerConfig>

    fun updatePlayerConfig(index: Int, playerConfig: PlayerConfig, playersInfo: List<PlayerConfig>): List<PlayerConfig>
}
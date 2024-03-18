package apps.tictactoe.logic.config

import apps.tictactoe.data.Game
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig

// TODO Extract there variables into a class and return that class when initialising the game and not the GameConfiguratorImpl
class GameConfiguratorImpl : GameConfigurator {
//    private var players: MutableList<PlayerConfig> = mutableListOf()
//    private var vsComputer: Boolean = false
//    private var aiDifficulty: GameAI? = null
//    private var currentPlayerIndex: Int = 0
//    private val selectedName: MutableList<String> = mutableListOf()
//    private val selectedSymbols: MutableList<Symbol> = mutableListOf()

//    private lateinit var game: Game

//    init {
//        println("GameConfigImpl init")
//        game = Game.init()
//        Symbol.initSymbols()
//    }

  override fun initializeForHumanPlayers(game: Game, numberOfPlayers: Int): Game {
    val players = mutableListOf<PlayerConfig>()
    val selectedName = mutableListOf<String>()
    val selectedSymbols = mutableListOf<Symbol>()
    val symbols = Symbol.getAllSymbols()
    val names = generateRandomNames()

    println("initializeForHumanPlayers start $numberOfPlayers")
    println(
      """
            initializeForHumanPlayers start
            no players: $numberOfPlayers
            names: $names
            symbols: $symbols
            players: $players
        """.trimIndent()
    )

    for (i in 0 until numberOfPlayers) {
      selectedName.add(names[i])
      selectedSymbols.add(symbols[i])
      players.add(PlayerConfig(name = names[i], symbol = symbols[i]))
    }
    println("initializeForHumanPlayers end")

    println("GameConfigImpl initializeForHumanPlayers: ${players.joinToString()}")

    return game.copy(
      players = players,
      vsComputer = false,
      numberOfPlayers = numberOfPlayers,
      currentPlayerIndex = 0,
      selectedNames = selectedName,
      selectedSymbols = selectedSymbols,
    )

//        return game
  }

//    override fun initializeForHumanPlayers1(numberOfPlayers: Int): List<PlayerConfig> {
//
//        vsComputer = false
//        players.clear()
//        Symbol.initSymbols(numberOfPlayers)
//        val symbols = Symbol.getAllSymbols()
//        val names = generateRandomNames(numberOfPlayers)
//
//        for (i in 0 until numberOfPlayers) {
//            selectedName.add(names[i])
//            selectedSymbols.add(symbols[i])
//            players.add(PlayerConfig(name = names[i], symbol = symbols[i]))
//        }
//        currentPlayerIndex = 0
//        println("GameConfigImpl initializeForHumanPlayers: ${players.joinToString()}")
//        return players
//    }

  // TODO Enable
//    override fun initializeForComputerPlayer(playerConfig: PlayerConfig, difficulty: GameAI) {
//        vsComputer = true
//        players.clear()
//        aiDifficulty = difficulty
//
//        Symbol.initSymbols(2)
//        val computerName = "Computer"
//        val computerSymbol = Symbol.create("C")
//        val playerName = playerConfig.name
//        val playerSymbol = playerConfig.symbol
//        players.add(PlayerConfig(name = playerName, symbol = playerSymbol))
//        players.add(PlayerConfig(name = computerName, symbol = computerSymbol))
//    }

  override fun updatePlayers(game: Game, numberOfPlayers: Int): Game {
    val players = mutableListOf<PlayerConfig>()
    val selectedName = mutableListOf<String>()
    val selectedSymbols = mutableListOf<Symbol>()
//    val x = MutableList(game.players)

    if (numberOfPlayers < game.numberOfPlayers) {
      println("updatePlayers less")
      for (i in 0 until numberOfPlayers) {
        players.add(game.players[i])
        selectedName.add(game.selectedNames[i])
        selectedSymbols.add(game.selectedSymbols[i])
      }
      println("updatePlayers less players: ${players.joinToString()}, no: $numberOfPlayers")
      return game.copy(
        players = players,
        numberOfPlayers = numberOfPlayers,
        selectedNames = selectedName,
        selectedSymbols = selectedSymbols
      )
    }
    println("updatePlayers more")

    val names = generateRandomNames().filter {
      println("nmaes: $it")
      it !in game.selectedNames
    }
    println("updatePlayers more name: ${names.joinToString()}")
    val symbols = Symbol.getAllSymbols().filter { it !in game.selectedSymbols }
    println("updatePlayers more symbol: ${symbols.joinToString()}")
    players.addAll(game.players)
    selectedName.addAll(game.selectedNames)
    selectedSymbols.addAll(game.selectedSymbols)
    println("${game.numberOfPlayers}, $numberOfPlayers")
    var j = 0
    for (i in game.numberOfPlayers until numberOfPlayers) {
      println("i: $i, j: $j")
      players.add(PlayerConfig(names[j], symbols[j]))
      selectedName.add(names[j])
      selectedSymbols.add(symbols[j])
      j = j.inc()
    }
//        players.add(PlayerConfig(name, symbol))
    println("updatePlayers more players: $players, no: $numberOfPlayers")
    return game.copy(
      players = players,
      numberOfPlayers = numberOfPlayers,
      selectedNames = selectedName,
      selectedSymbols = selectedSymbols
    )
  }

  override fun updatePlayerConfig(game: Game, index: Int, playerConfig: PlayerConfig): Game {
//        println("GameConfigImpl: ${players.joinToString()}")
//        val selectedSymbol = playerConfig.symbol
//        players = players.mapIndexed { mapIndex, mapPlayerConfig ->
//            if (index == mapIndex) playerConfig
//            else {
//                if (mapPlayerConfig.symbol == selectedSymbol)
//            }
//        }
    val players = mutableListOf<PlayerConfig>()
    players.addAll(game.players)
    players[index] = playerConfig
//        players[index].copy(name = playerConfig.name, symbol = playerConfig.symbol)
    println("GCImpl players: ${players.joinToString()}")
    return game.copy(players = players)
  }

  private fun generateRandomNames(): List<String> {
    val names = listOf("Alice", "Bob", "Charlie", "Dave", "Eve"/*, "Mike", "Jack"*/) // Add more as needed
//    if (size > names.size) {
//      throw IllegalArgumentException("Not enough unique symbols for the number of players")
//    }
    return names.shuffled()
  }

}
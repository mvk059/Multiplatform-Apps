package apps.tictactoe.data

class Symbol private constructor(val value: String) {

  companion object {
    private val allowedSymbols = mutableListOf<Symbol>()

    fun initSymbols() {
      val possibleSymbols = listOf("X", "O", "I", "V", "@", "#", "*") // Add more if needed
//      if (size > possibleSymbols.size) {
//        throw IllegalArgumentException("Not enough unique symbols for the number of players")
//      }
      resetSymbols()
      possibleSymbols.shuffled().forEach { create(it) }
    }

    fun create(value: String): Symbol {
      if (value.isEmpty()) throw IllegalArgumentException("Symbol cannot be empty.")
      if (allowedSymbols.any { it.value == value }) throw IllegalArgumentException("Symbol $value is already in use.")

      return Symbol(value).also { allowedSymbols.add(it) }
    }

    fun String.getSymbol(): Symbol? {
      return allowedSymbols.firstOrNull { it.value == this }
    }

    fun resetSymbols() {
      allowedSymbols.clear()
    }

    fun getAllSymbols(): List<Symbol> = allowedSymbols

    fun getSymbolsSize() = allowedSymbols.size
  }

}

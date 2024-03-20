package navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.starfield.StarField
import apps.tictactoe.TicTacToeIntro

@Composable
fun AppNavigation() {
  val navController = rememberNavController()
  val navigationController = rememberNavigationController(navController)

  NavHost(navController = navController, startDestination = Screens.Home.name) {
    composable(Screens.Home.name) {
      HomeScreen(navigationController)
    }
    composable(Screens.StarField.name) {
      StarField()
    }
    composable(Screens.TicTacToe.name) {
      TicTacToeIntro()
    }
  }
}

@Composable
fun rememberNavigationController(navController: androidx.navigation.NavController): NavigationController {
  return remember(navController) { NavigationController(navController) }
}

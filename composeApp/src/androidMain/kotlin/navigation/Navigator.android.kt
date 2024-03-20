package navigation

actual class NavigationController(private val navController: androidx.navigation.NavController) {
  actual fun navigateTo(route: Screens) {
    navController.navigate(route.name)
  }
}
package navigation

import kotlinx.browser.window

//// Web-specific implementation
//object WebNavigationController : NavigationController {
//    override fun navigateTo(route: Screens) {
//        window.location.href = "#$route" // Hash-based or use History API for cleaner URLs
//    }
//
//}
//
//// Android example
//actual fun provideNavigationController(navController: NavController): NavigationController =
//    navigation.WebNavigationController(navController)

actual class NavigationController {
    actual fun navigateTo(route: Screens) {
        // Use kotlinx.browser.window to change location or manipulate history
        println("NavigationController: ${route.route}")
        window.location.hash = route.route
    }
}
package navigation

import kotlinx.browser.window

actual class NavigationController {
    actual fun navigateTo(route: Screens) {
        // Use kotlinx.browser.window to change location or manipulate history
        println("NavigationController: ${route.route}")
        window.location.hash = route.route
    }
}
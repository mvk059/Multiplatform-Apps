import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import apps.starfield.DetailScreen
import apps.starfield.NotFoundScreen
import navigation.Screens.*
import apps.starfield.StarField
import apps.tictactoe.ui.TicTacToe
import kotlinx.browser.window
import navigation.NavigationController
import navigation.Screens
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val navigationController = NavigationController()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        ComposeApp(navigationController)
//        App(navigationController)
    }
}

@Composable
fun ComposeApp(navigationController: NavigationController) {
    // This state holds the current route. It's updated whenever the URL hash changes.
    val initialRoute = if (window.location.hash.isNotEmpty()) window.location.hash.removePrefix("#") else Home.route
    val currentRoute = remember { mutableStateOf(initialRoute) }

    // Use DisposableEffect to set up a listener for URL hash changes. This ensures that
    // the app responds to navigation actions (like back/forward browser buttons).
    DisposableEffect(Unit) {
        val onHashChange: (Event) -> Unit = {
            // Update the current route based on the new hash.
            println("onHashChange: ${window.location.hash.removePrefix("#")}")
            currentRoute.value = window.location.hash.removePrefix("#")
        }

        // Add the hash change listener to the window object.
        window.addEventListener("hashchange", onHashChange)

        // The onDispose block is called when the composable leaves the composition,
        // which is where we remove the event listener to avoid memory leaks.
        onDispose {
            window.removeEventListener("hashchange", onHashChange)
        }
    }

    // Based on the current route, we decide which screen to display.
    // For simplicity, let's assume we have two routes: "home" and "details".
    println("WASM Route: ${currentRoute.value}, Home: ${Home.route}, Star: ${StarField.route}")
    when (currentRoute.value) {
        "", Home.route -> HomeScreen(navigationController)
        StarField.route -> StarField()
        TicTacToe.route -> TicTacToe()
        else -> NotFoundScreen(navigationController)
    }
}

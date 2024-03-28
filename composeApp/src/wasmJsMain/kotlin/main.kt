import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.CanvasBasedWindow
import apps.starfield.NotFoundScreen
import navigation.Screens.*
import apps.starfield.StarField
import apps.tictactoe.TicTacToeIntro
import apps.tictactoe.ui.theme.Karla
import apps.tictactoe.ui.theme.Montserrat
import kotlinx.browser.window
import kotlinx.coroutines.delay
import navigation.NavigationController
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val navigationController = NavigationController()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        var isAppLoaded by remember { mutableStateOf(false) }

        // Use LaunchedEffect to simulate app loading
        LaunchedEffect(Unit) {
            loadMontserratFont()
            loadKarlaFont()
            delay(3000) // Simulating a 3-second delay
            isAppLoaded = true
        }

        // Your app content
        if (isAppLoaded) {
            // Display your main app content
            ComposeApp(navigationController)
        } else {
            LoadingScreen()
        }
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
        TicTacToe.route -> TicTacToeIntro()
        else -> NotFoundScreen(navigationController)
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

private suspend fun loadMontserratFont() {
    val regular = loadResource("/montserrat_regular.ttf").toByteArray()
    val medium = loadResource("/montserrat_medium.ttf").toByteArray()
    val light = loadResource("/montserrat_light.ttf").toByteArray()
    val semiBold = loadResource("/montserrat_semibold.ttf").toByteArray()

    Montserrat = FontFamily(
        Font(identity = "MontserratRegular", data = regular, weight = FontWeight.Normal),
        Font(identity = "MontserratMedium", data = medium, weight = FontWeight.Medium),
        Font(identity = "MontserratLight", data = light, weight = FontWeight.Light),
        Font(identity = "MontserratSemiBold", data = semiBold, weight = FontWeight.SemiBold),
    )
}

private suspend fun loadKarlaFont() {
    val regular = loadResource("/karla_regular.ttf").toByteArray()
    val bold = loadResource("/karla_bold.ttf").toByteArray()

    Karla = FontFamily(
        Font(identity = "KarlaRegular", data = regular, weight = FontWeight.Normal),
        Font(identity = "KarlaBold", data = bold, weight = FontWeight.Bold),
    )
}
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import apps.starfield.NotFoundScreen
import apps.starfield.StarField
import apps.tictactoe.TicTacToeIntro
import apps.tictactoe.ui.theme.Karla
import apps.tictactoe.ui.theme.Montserrat
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.h1
import kotlinx.html.p
import navigation.NavigationController
import navigation.Screens
import navigation.Screens.Home
import navigation.Screens.TicTacToe
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    document.getElementById("updateComposeButton")?.let { button ->
        (button as HTMLButtonElement).onclick = {
            updateComposeFromJS("Updated from HTML button!")
        }
    }

    val navigationController = NavigationController()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        // Load fonts
        LaunchedEffect(Unit) {
            loadMontserratFont()
            loadKarlaFont()
        }

        // Observe window size changes
        val windowSize = remember { mutableStateOf(getWindowSize()) }
        listenForWindowResize {
            windowSize.value = it
        }

        val (width, height) = getCanvasSize()

        BoxWithConstraints(modifier = Modifier.size(width, height).padding(10.dp)) {
            composeApp(navigationController)
        }
    }

    renderHtmlContent()

}


@Composable
fun composeApp(navigationController: NavigationController) {
    // This state holds the current route. It's updated whenever the URL hash changes.
    val initialRoute = if (window.location.hash.isNotEmpty()) window.location.hash.removePrefix("#") else Home.route
    val currentRoute = remember { mutableStateOf(initialRoute) }

    observeNavigation {
        currentRoute.value = it
    }

    Column {
        // Display the hovered section
        var text by remember { mutableStateOf("Hello, World!") }

        Button(onClick = {
            updateElement("myElement", "Updated from Compose!")
            jsAlert("Button clicked!")
        }) {
            Text("Alert HTML")
        }

        // Set up the update function
        LaunchedEffect(Unit) {
            setUpdateFunction { newValue ->
                text = newValue
            }
        }

        // Set up hover handlers for each section
        for (i in 1..3) {
            document.getElementById("section$i")?.let { section ->
                (section as HTMLElement).onmouseenter = {
                    updateComposeFromJS(i.toString())
                }
            }
        }

        // Your Compose UI
        Text(text)

        when(text) {
            "1" -> HomeScreen(navigationController)
            "2" -> StarField()
            "3" -> TicTacToeIntro()
            else -> HomeScreen(navigationController)
        }

        when (currentRoute.value) {
            "", Home.route -> HomeScreen(navigationController)
            Screens.StarField.route -> StarField()
            TicTacToe.route -> TicTacToeIntro()
            else -> NotFoundScreen(navigationController)
        }
    }
}

@Composable
private fun observeNavigation(onWindowChanged: (String) -> Unit) {
    // Use DisposableEffect to set up a listener for URL hash changes. This ensures that
    // the app responds to navigation actions (like back/forward browser buttons).
    DisposableEffect(Unit) {
        val onHashChange: (Event) -> Unit = {
            // Update the current route based on the new hash.
            onWindowChanged(window.location.hash.removePrefix("#"))
        }

        // Add the hash change listener to the window object.
        window.addEventListener("hashchange", onHashChange)

        // The onDispose block is called when the composable leaves the composition,
        // which is where we remove the event listener to avoid memory leaks.
        onDispose {
            window.removeEventListener("hashchange", onHashChange)
        }
    }
}

fun renderHtmlContent() {
    val htmlContent = document.getElementById("htmlContent") as HTMLElement
    htmlContent.appendChild(document.create.div {
        h1 { +"Welcome to the HTML side" }
        p { +"This is regular HTML content created using Kotlin's HTML DSL." }
        // Add more HTML content as needed
    })
}

fun getCanvasSize(): Pair<Dp, Dp> {
    val container = document.getElementById("canvasContainer") as HTMLElement
    val rect = container.getBoundingClientRect()
    val width = Dp(rect.width.toFloat())
    val height = Dp(rect.height.toFloat())
    return width to height
}

@Composable
private fun listenForWindowResize(onSizeChanged: (Pair<Int, Int>) -> Unit) {
    DisposableEffect(Unit) {
        val resizeListener: (Event) -> Unit = {
            onSizeChanged(getWindowSize())
        }
        window.addEventListener("resize", resizeListener)
        onDispose {
            window.removeEventListener("resize", resizeListener)
        }
    }
}

private fun getWindowSize(): Pair<Int, Int> {
    return Pair(window.innerWidth, window.innerHeight)
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

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import apps.starfield.StarField
import apps.tictactoe.TicTacToeIntro
import apps.tictactoe.ui.theme.Karla
import apps.tictactoe.ui.theme.Montserrat
import kotlinx.browser.document
import kotlinx.browser.window
import navigation.NavigationController
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val navigationController = NavigationController()

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        // Load fonts
        LaunchedEffect(Unit) {
            loadMontserratFont()
            loadKarlaFont()
        }

        // Observe window size changes
        val windowSize = remember { mutableStateOf(getWindowSize()) }
        val canvasSize = remember { mutableStateOf(getCanvasSize()) }
        listenForWindowResize {
            windowSize.value = it
        }

        BoxWithConstraints(modifier = Modifier.size(canvasSize.value.first, canvasSize.value.second)) {
            composeApp(navigationController)
        }
    }
}

@Composable
fun composeApp(navigationController: NavigationController) {

    // Display the hovered section
    val hoverItem = remember { mutableStateOf(1) }

    observeHoverState { newValue ->
        hoverItem.value = newValue
    }

    when (hoverItem.value) {
        1 -> HomeScreen(navigationController)
        2 -> StarField()
        3 -> TicTacToeIntro()
        else -> HomeScreen(navigationController)
    }
}

private fun observeHoverState(onHoverItemChanged: (Int) -> Unit) {
    // Set up hover handlers for each section
    for (i in 1..3) {
        document.getElementById("section$i")?.let { section ->
            (section as HTMLElement).onmouseenter = {
                onHoverItemChanged(i)
            }
        }
    }
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

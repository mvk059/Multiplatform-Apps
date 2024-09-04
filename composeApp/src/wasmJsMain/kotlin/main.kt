import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import apps.starfield.StarField
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement

/*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
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
import kotlinx.browser.document
import kotlinx.browser.window
import navigation.NavigationController
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlinx.html.*
import kotlinx.html.dom.*
import org.w3c.dom.HTMLCanvasElement
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import utils.pxToDp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val navigationController = NavigationController()
    val canvasSize = mutableStateOf(IntSize(0, 0))

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        LaunchedEffect(Unit) {
            loadMontserratFont()
            loadKarlaFont()
        }

        // Observe window size changes
        val windowSize = remember { mutableStateOf(getWindowSize()) }
        println("windowSize wxh: ${windowSize.value.first}x${windowSize.value.second}")
        DisposableEffect(Unit) {
            val resizeListener: (Event) -> Unit = {
                windowSize.value = getWindowSize()
                println("windowSize changes wxh: ${windowSize.value.first}x${windowSize.value.second}")
            }
            window.addEventListener("resize", resizeListener)
            onDispose {
                window.removeEventListener("resize", resizeListener)
            }
        }

        // Initial canvas size update
        LaunchedEffect(Unit) {
            updateCanvasSize {
                canvasSize.value = it
            }
        }

        // Use the canvasSize to scale the ComposeApp
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
            ComposeApp(navigationController, canvasSize.value)
//        }
    }

    renderHtmlContent()
}

@Composable
fun ComposeApp(navigationController: NavigationController, size: IntSize) {
    println("ComposeApp size: ${size.width}x${size.height}")
    // Get the current size of the composable area
//    val density = androidx.compose.ui.platform.LocalDensity.current
//    val composableSize = remember { mutableStateOf(Size.Zero) }

//    BoxWithConstraints(
//        modifier = Modifier.size(500.dp)
//    ) {
//        val screenWidthDp = maxWidth
//        val screenHeightDp = maxHeight
//        println("Screen size max ${maxWidth} x ${maxHeight}")
        println("Screen size size ${size.width} x ${size.height}")
//        println("Screen size in dp: ${screenWidthDp} x ${screenHeightDp}")

        // This state holds the current route. It's updated whenever the URL hash changes.
        val initialRoute =
            if (window.location.hash.isNotEmpty()) window.location.hash.removePrefix("#") else Home.route
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
            "", Home.route -> StarField(/*Modifier.size(screenWidthDp, screenHeightDp)*/) //HomeScreen(navigationController)
            StarField.route -> StarField()
            TicTacToe.route -> TicTacToeIntro()
            else -> NotFoundScreen(navigationController)
        }

//    }
}

fun updateCanvasSize(onCanvasSizeChanged: (IntSize) -> Unit) {
    val container = document.getElementById("canvasContainer") as HTMLElement
    val canvas = document.getElementById("ComposeTarget") as HTMLCanvasElement
    val rect = container.getBoundingClientRect()
    canvas.width = rect.width.toInt()
    canvas.height = rect.height.toInt()
    onCanvasSizeChanged(IntSize(canvas.width, canvas.height))
    println("Canvas size updated: ${canvas.width}x${canvas.height}")
}

fun renderHtmlContent() {
    val htmlContent = document.getElementById("htmlContent") as HTMLElement
    htmlContent.appendChild(document.create.div {
        h1 { +"Welcome to the HTML side" }
        p { +"This is regular HTML content created using Kotlin's HTML DSL." }
        // Add more HTML content as needed
    })
}


fun getWindowSize(): Pair<Int, Int> {
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

 */


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {

        println("CanvasBasedWindow: ${getWindowSize()}")
        val container = document.getElementById("canvasContainer") as HTMLElement
        val rect = container.getBoundingClientRect()
        val w = Dp(rect.width.toFloat())
        val h = Dp(rect.height.toFloat())
        println("container: $w x $h")

        BoxWithConstraints(modifier = Modifier.size(w, h)) {

            println("BoxWithConstraints max $maxWidth x $maxHeight")
            StarField(modifier = Modifier.padding(20.dp))
            /*
           Column(modifier = Modifier.size(maxWidth, maxHeight)) {

               Text(
                   "This is Wasm content rendered by Compose",
                   fontSize = 20.sp,
               )

               Row(
                   modifier = Modifier.fillMaxWidth().background(color = Color.Magenta),
                   horizontalArrangement = Arrangement.SpaceAround,
               ) {
                   Text("ONE", fontSize = 30.sp)
                   Text("TWO", fontSize = 30.sp)
                   Text("THREE", fontSize = 30.sp)
               }

           }
            */

        }
    }
}

fun getWindowSize(): Pair<Int, Int> {
    return Pair(window.innerWidth, window.innerHeight)
}

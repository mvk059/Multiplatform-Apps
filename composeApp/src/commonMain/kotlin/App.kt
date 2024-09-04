import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import apps.tictactoe.ui.theme.Typography
import multiplatformapps.composeapp.generated.resources.Res
import multiplatformapps.composeapp.generated.resources.compose_multiplatform
import navigation.NavigationController
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App2() {
  MaterialTheme {
    var showContent by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
      Button(onClick = { showContent = !showContent }) {
        Text("Click me!")
      }
      AnimatedVisibility(showContent) {
        val greeting = remember { Greeting().greet() }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
          Image(painterResource(Res.drawable.compose_multiplatform), null)
          Text("Compose: $greeting")
        }
      }
    }
  }
}

@Composable
fun HomeScreen(navigationController: NavigationController) {
  MaterialTheme(
    typography = Typography,
  ) {
    Column(
      modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
    ) {
      Button(
        onClick = { navigationController.navigateTo(Screens.StarField) },
        content = { Text("StarField!", fontSize = 16.sp) }
      )
      Button(
        onClick = { navigationController.navigateTo(Screens.TicTacToe) },
        content = { Text("TicTacToe", fontSize = 16.sp) }
      )
      // Include other UI elements that were initially part of the App composable
    }
  }
}

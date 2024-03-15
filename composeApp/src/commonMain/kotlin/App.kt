import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import multiplatformapps.composeapp.generated.resources.Res
import multiplatformapps.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import components.AppBar
import navigation.NavigationController
import navigation.Screens

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
  MaterialTheme {
    Column(
      modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
    ) {
      Button(
        onClick = { navigationController.navigateTo(Screens.StarField) },
        content = { Text("StarField!") }
      )
//      Button(
//        onClick = { navigationController.navigateTo(Screens.Home) },
//        content = { Text("Home") }
//      )
      // Include other UI elements that were initially part of the App composable
    }
  }
}

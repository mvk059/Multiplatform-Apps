package apps.starfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import navigation.NavigationController
import navigation.Screens

@Composable
fun DetailScreen(navigationController: NavigationController) {
    println("DetailScreen show")
    // Example HomeScreen that contains a button to navigate to the DetailsScreen.
    Button(
        modifier = Modifier.fillMaxSize().background(color = Color.Cyan),
        onClick = { navigationController.navigateTo(Screens.Home) },
        content = { Text("Go to Details") }
    )
}
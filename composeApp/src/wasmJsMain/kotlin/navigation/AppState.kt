package navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

//@Stable
//actual class AppState(
//    actual val scaffoldState: ScaffoldState,
//) {
//
//    private val navigationStack = NavigationStack(Screens.Home.route)
//
//    actual val currentRoute: String?
//        get() = navigationStack.lastWithIndex().value
//
//    fun add(screen: Screens) {
//        navigationStack.push(screen.route)
//    }
//
//    fun remove() {
//        navigationStack.back()
//    }
//}
//
//@Composable
//actual fun rememberAppState(): AppState {
//    val scaffoldState = rememberScaffoldState()
//    return AppState(scaffoldState)
//}
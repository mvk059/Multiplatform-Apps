package apps.tictactoe.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.ui.theme.Design
import multiplatformapps.composeapp.generated.resources.Res
import multiplatformapps.composeapp.generated.resources.ic_arrow_drop_down_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

//@Composable
//actual fun <T> DropdownSelector(
//  label: String,
//  items: List<T>,
//  selectedItem: T,
//  onItemSelected: (T) -> Unit
//) {
//
//  CustomDropdown(
//    options = items,
//    selectedOption = selectedItem,
//    onOptionSelected = { onItemSelected(it) }
//  )
//}
//
//@Composable
//fun <T> CustomDropdown(
//  options: List<T>,
//  selectedOption: T,
//  onOptionSelected: (T) -> Unit
//) {
//  var isExpanded by remember { mutableStateOf(false) }
//  val transitionState = remember { MutableTransitionState(isExpanded) }
//  transitionState.targetState = isExpanded
//
//  // Container for the dropdown
//  Box {
//
//    TicButton(
//      text = selectedOption.toString(),
//      onClick = { isExpanded = !isExpanded },
//    )
//
//    // Dropdown Expansion Animation
//    AnimatedVisibility(
//      visibleState = transitionState,
//      enter = expandHorizontally() + fadeIn(),
//      exit = shrinkHorizontally() + fadeOut()
//    ) {
//
//      Row {
//
//        options.forEach { option ->
//          TicButton(
//            text = option.toString(),
//            onClick = {
//              onOptionSelected(option)
//              isExpanded = false
//            }
//          )
//        }
//      }
//    }
//  }
//}

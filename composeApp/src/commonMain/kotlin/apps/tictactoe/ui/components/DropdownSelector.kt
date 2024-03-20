package apps.tictactoe.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*

@Composable
fun <T> DropdownSelector(
  label: String,
  items: List<T>,
  selectedItem: T,
  onItemSelected: (T) -> Unit
) {

  CustomDropdown(
    options = items,
    selectedOption = selectedItem,
    onOptionSelected = { onItemSelected(it) }
  )
}

@Composable
fun <T> CustomDropdown(
  options: List<T>,
  selectedOption: T,
  onOptionSelected: (T) -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }
  val transitionState = remember { MutableTransitionState(isExpanded) }
  transitionState.targetState = isExpanded

  // Container for the dropdown
  Box {

    TicButton(
      text = selectedOption.toString(),
      onClick = { isExpanded = !isExpanded },
    )

    // Dropdown Expansion Animation
    AnimatedVisibility(
      visibleState = transitionState,
      enter = expandHorizontally() + fadeIn(),
      exit = shrinkHorizontally() + fadeOut()
    ) {

      Row {

        options.forEach { option ->
          TicButton(
            text = option.toString(),
            onClick = {
              onOptionSelected(option)
              isExpanded = false
            }
          )
        }
      }
    }
  }
}

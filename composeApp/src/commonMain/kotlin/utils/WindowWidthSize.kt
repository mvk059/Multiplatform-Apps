package utils

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

sealed interface WindowWidthSize {
  data object Compact: WindowWidthSize
  data object Medium: WindowWidthSize
  data object Expanded: WindowWidthSize
}

fun WindowSizeClass.mapToWindowWidthSize(): WindowWidthSize {
  return when(this.widthSizeClass) {
    WindowWidthSizeClass.Compact -> WindowWidthSize.Compact
    WindowWidthSizeClass.Medium -> WindowWidthSize.Medium
    WindowWidthSizeClass.Expanded -> WindowWidthSize.Expanded
    else -> WindowWidthSize.Compact
  }
}
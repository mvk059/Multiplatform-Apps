package apps.tictactoe.ui.components

import androidx.compose.runtime.Composable

expect @Composable
fun <T> DropdownSelector(label: String, items: List<T>, selectedItem: T, onItemSelected: (T) -> Unit)

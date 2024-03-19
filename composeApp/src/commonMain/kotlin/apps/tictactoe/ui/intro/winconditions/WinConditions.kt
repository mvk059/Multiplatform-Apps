package apps.tictactoe.ui.intro.winconditions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.tictactoe.logic.enums.WinCondition
import apps.tictactoe.ui.components.TicButton
import apps.tictactoe.ui.theme.Design

@Composable
fun WinConditions(
  modifier: Modifier = Modifier,
  winConditions: Set<WinCondition>,
  onWinConditionUpdate: (WinCondition) -> Unit,
) {

  Column(
    modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    content = {

      Text(
        text = "Rules",
        modifier = Modifier.padding(top = 8.dp),
        color = Design.whiteColor,
        fontSize = 32.sp,
      )

      Spacer(Modifier.height(16.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {

        WinCondition.entries.forEach { condition ->

          TicButton(
            text = condition.name.replaceFirstChar { if (it.isUpperCase()) it.titlecase() else it.toString() },
            onClick = { onWinConditionUpdate(condition) },
            greyOut = winConditions.contains(condition),
          )

          Spacer(Modifier.width(8.dp))
        }
      }
    }
  )
}
package apps.tictactoe.ui.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import apps.tictactoe.logic.playmode.PlayMode
import apps.tictactoe.ui.components.TicButton

@Composable
fun PlayerSelection(
  modifier: Modifier = Modifier,
  onPlayModeSelected: (PlayMode) -> Unit,
) {

  Row(
    modifier = modifier.fillMaxWidth(),
//      .background(shape = RectangleShape, color = Color.Cyan),
    horizontalArrangement = Arrangement.SpaceBetween,
    content = {

      Spacer(Modifier.weight(0.2f))

      TicButton(
        text = "Play against Humans",
        onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_HUMAN) }
      )

      Spacer(modifier = Modifier.width(16.dp))

      TicButton(
        text = "Play against Computer",
        onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_COMPUTER) }
      )

      Spacer(Modifier.weight(0.2f))
    }
  )

}
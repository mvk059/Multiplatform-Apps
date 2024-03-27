package apps.tictactoe.ui.intro.player

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import apps.tictactoe.data.enums.PlayMode
import apps.tictactoe.ui.components.TicButton
import utils.WindowWidthSize

@Composable
fun PlayerSelection(
  modifier: Modifier = Modifier,
  windowWidthSize: WindowWidthSize,
  onPlayModeSelected: (PlayMode) -> Unit,
) {

  when (windowWidthSize) {
    WindowWidthSize.Compact -> {
      Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {

          TicButton(
            text = "Play against Humans",
            onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_HUMAN) }
          )

          Spacer(modifier = Modifier.height(16.dp))

          TicButton(
            text = "Play against Computer",
            onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_COMPUTER) }
          )

        }
      )
    }

    WindowWidthSize.Medium, WindowWidthSize.Expanded -> {
      Row(
        modifier = modifier.fillMaxWidth(),
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
  }

//  Row(
//    modifier = modifier.fillMaxWidth(),
////      .background(shape = RectangleShape, color = Color.Cyan),
//    horizontalArrangement = Arrangement.SpaceBetween,
//    content = {
//
//      Spacer(Modifier.weight(0.2f))
//
//      TicButton(
//        text = "Play against Humans",
//        onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_HUMAN) }
//      )
//
//      Spacer(modifier = Modifier.width(16.dp))
//
//      TicButton(
//        text = "Play against Computer",
//        onClick = { onPlayModeSelected(PlayMode.HUMAN_VS_COMPUTER) }
//      )
//
//      Spacer(Modifier.weight(0.2f))
//    }
//  )

}

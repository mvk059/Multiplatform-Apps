package apps.consistenthashing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextLine
import kotlin.math.cos
import kotlin.math.sin

data class Server(val id: Int, val values: MutableList<String> = mutableListOf())

@Composable
fun ConsistentHashingVisualization() {
    var servers by remember { mutableStateOf(listOf<Server>()) }
    var inputValue by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)) {
        Text(
            "Consistent Hashing",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 30.sp),
        )

        Spacer(Modifier.height(4.dp))

        Text(
            "Work in Progress",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 10.sp),
        )

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { servers = servers + Server(servers.size) }) {
                    Text("Add Server")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    if (servers.isNotEmpty()) {
                        val removedServer = servers.last()
                        servers = servers.dropLast(1)
                        // Remap the values of the removed server to the remaining servers
                        removedServer.values.forEach { value ->
                            val hash = value.hashCode()
                            val serverIndex =
                                if (servers.isNotEmpty()) (hash % servers.size + servers.size) % servers.size else 0
                            val server = if (servers.isNotEmpty()) servers[serverIndex] else null
                            server?.values?.add(value)
                        }
                    }
                }) {
                    Text("Remove Server")
                }
            }
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = { Text("Enter a value") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    if (inputValue.isNotEmpty() && servers.isNotEmpty()) {
                        val hash = inputValue.hashCode()
                        val serverIndex = (hash % servers.size + servers.size) % servers.size
                        val server = servers[serverIndex]
                        server.values.add(inputValue)
                        inputValue = ""
                    }
                }) {
                    Text("Add Value")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.width / 4 - 50.dp.toPx()

                // Draw the circle with a green stroke
                drawCircle(
                    color = Color.Green,
                    radius = radius,
                    center = center,
                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                )

                // Draw the servers and their assigned values
                servers.forEachIndexed { index, server ->
                    val angle = (index * 2 * kotlin.math.PI) / servers.size
                    val x = center.x + (radius * cos(angle)).toFloat()
                    val y = center.y + (radius * sin(angle)).toFloat()
                    drawCircle(Color.Cyan, 20.dp.toPx(), Offset(x, y))

                    val paint = Paint().apply {
                        color = org.jetbrains.skia.Color.BLACK
                    }
                    val font = Font().apply {
                        size = 16.dp.toPx()
                    }
                    val serverText = "${server.id}"
                    val textLine = TextLine.make(serverText, font)
                    val textWidth = textLine.width
                    val textHeight = textLine.height
                    val textX = x - textWidth / 2
                    val textY = y + textHeight / 2
                    drawContext.canvas.nativeCanvas.drawTextLine(textLine, textX, textY, paint)

                    // Draw the assigned values beside the server circle
                    server.values.forEachIndexed { valueIndex, value ->
                        val valueX = x + 30.dp.toPx()
                        val valueY = y + (valueIndex * 20.dp.toPx())
                        drawContext.canvas.nativeCanvas.drawString(value, valueX, valueY, font, paint)
                    }
                }
            }
        }
    }
}
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
fun updateElement(id: String, text: String) {
    val element = document.getElementById(id) as? HTMLElement
    element?.innerText = text
}

external fun jsAlert(message: String)

private var updateComposeState: ((String) -> Unit)? = null

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("setUpdateFunction")
fun setUpdateFunction(update: (String) -> Unit) {
    updateComposeState = update
}

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("updateComposeFromJS")
fun updateComposeFromJS(newValue: String) {
    updateComposeState?.invoke(newValue)
}

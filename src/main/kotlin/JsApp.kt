
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.common.padding
import cx.js.ui.framework.widgets.dom.render
import cx.ui.framework.widget.Widget
import flutter.Border
import kotlin.browser.document

fun main(args: Array<String>) {
  render(document.getElementById("content")!!) {
    +JsApp()
  }
}

class JsApp : Widget<String>("app") {
  val url = document.location?.hash?.trim()?.replace("-", " ")

  override val initialState: String
    get() = if (url in samples) {
      url!!
    } else {
      document.location!!.hash = samples.keys.first()
      samples.keys.first()
    }

  override fun render(state: String) = Column {
    +Row {
      samples.forEach { sample ->
        +Button(
            key = sample.key,
            disabled = sample.key == state,
            onClick = { showSample(sample.key) }
        ) {
          +sample.key
        }
      }
    }
    +Row {
      +Box(
          border = Border(1.0),
          padding = 10.padding
      ) {
        val sample = samples[state]!!
        +sample()
      }
    }

    val num = samples.keys.indexOf(state)
    val packageName = state.replace(" ","_")
    val src = "https://raw.githubusercontent.com/snrostov/ui-dsl/master/exportToHTML/examples/p0${num}_$packageName/main.kt.html"
    showSources(src)
  }

  fun showSample(id: String) {
    document.location!!.hash = id.replace(" ", "-")
    updateState { id }
  }

  companion object {
    val samples = mapOf(
        "hello world" to { examples.p00_hello_world.render() },
        "hello widgets" to { examples.p01_hello_widgets.render() },
        "hello widget body" to { examples.p02_hello_widget_body.render() },
        "hello state" to { examples.p03_hello_state.render() },
        "hello box" to { examples.p04_hello_box.render() }
    )
  }
}

external fun showSources(src: String)
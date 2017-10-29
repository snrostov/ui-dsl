package react.p03_hello_state

import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Margin
import cx.js.ui.framework.widgets.common.Padding
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.dom.render
import cx.ui.framework.widget.Widget
import flutter.Border
import flutter.TextAlign
import kotlin.browser.document

fun main(args: Array<String>) {
  val container = document.getElementById("content")!!

  render(container) {
    +MyCounter("counter1", 1)
    +MyCounter("counter2", 2)
  }
}

class MyCounter(key: String, override val initialState: Int) : Widget<Int>(key) {
  override fun render(state: Int): RenderObject? = Box(
      border = Border(),
      margin = Margin(5.0, 2.0, 5.0, 2.0),
      padding = Padding(5.0, 2.0, 5.0, 2.0)
  ) {
    +Row {
      +"count: "

      +Button("dec", onClick = ::dec) {
        +"-"
      }

      +Text(align = TextAlign.center) {
        +Box(width = 50.0) {
          +state
        }
      }

      +Button("inc", onClick = ::inc) {
        +"+"
      }
    }
  }

  private fun inc() {
    updateState {
      this + 1
    }
  }

  private fun dec() {
    updateState {
      this - 1
    }
  }
}
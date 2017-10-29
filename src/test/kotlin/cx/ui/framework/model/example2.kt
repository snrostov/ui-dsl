package cx.ui.framework.model

import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Margin
import cx.js.ui.framework.widgets.common.Padding
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.dom.render
import cx.ui.framework.model.widgets.input.CustomInput
import cx.ui.framework.widget.StatelessWidget
import flutter.Border
import flutter.BorderStyle
import flutter.TextAlign
import flutter.black
import react.ReactElement
import kotlin.browser.document

class MyWidget(val userName: String) : StatelessWidget(userName) {
  private val internalProperty = userName

  override fun render(): ReactElement? = Column {
    +Row("greeting") {
      +"Hello, "
      +userName
      +"! There is counter for you: "
      +MyCounter("counter", Var(1))
    }
  }
}

class MyCounter(key: String, val value: Var<Int>) : StatelessWidget(key) {
  override fun render(): RenderObject? {
    return Box(
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
            +value
          }
        }

        +Button("inc", onClick = ::inc) {
          +"+"
        }
      }
    }
  }

  private fun inc() {
    value.update {
      this + 1
    }
  }

  private fun dec() {
    value.update {
      this - 1
    }
  }
}

fun run() {
  val container = document.getElementById("content")!!

  val counterValue = Var(10)

  render(container) {
    +Box(border = Border(1.0, BorderStyle.solid, black)) {
      +Column {
        +"Auto counter:"
        +MyWidget("ivan")
        +"Another counter:"
        +MyCounter("counter2", counterValue)
        +MyCounter("counter1", counterValue)
        +CustomInput.int("input", counterValue)
      }
    }
  }
}
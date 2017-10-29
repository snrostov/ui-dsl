package react

import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Margin
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.events.MouseListener
import cx.js.ui.framework.widgets.common.ext.BulletList
import cx.js.ui.framework.widgets.common.ext.ListItem
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.common.padding
import cx.js.ui.framework.widgets.elements
import cx.ui.framework.widget.StatelessWidget
import flutter.TextStyle
import flutter.bold
import flutter.rgbColor
import flutter.translate
import mocha.describe
import mocha.invoke
import mocha.it
import org.w3c.dom.events.MouseEvent

fun main(args: Array<String>) {
  describe("Element builder") {
    it("should works") {
      elements {
        Box(transform = translate(1f, 1f)) {
          +"test"
        }
      }
    }
  }
}

class MyPage(key: String) : StatelessWidget(key) {
  override fun render() = Column {
    +Text(
        text = "Page title",
        style = TextStyle(
            fontSize = 24.0,
            fontWeight = bold
        )
    )

    +Box(margin = Margin(left = 10.0)) {
      +Row {
        +Box(padding = 10.padding) {
          +Column {
            +BulletList {
              +MenuItem("1", "About")
              +MenuItem("2", "Documentation")
            }
          }
        }
      }
    }
  }
}

class MenuItem(
    key: String,
    val title: String
) : StatelessWidget(key) {
  override fun render() = ListItem {
    +Text(color = 0xFF0000.rgbColor) {
      +MouseListener(click = ::click) {
        +title
      }
    }
  }

  private fun click(mouseEvent: MouseEvent) {
  }
}
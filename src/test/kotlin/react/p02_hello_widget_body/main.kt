package react.p02_hello_widget_body

import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.dom.render
import cx.ui.framework.WidgetBody
import cx.ui.framework.widget.StatelessWidget
import react.p01_hello_widgets.Header
import kotlin.browser.document

fun main(args: Array<String>) {
  val container = document.getElementById("content")!!

  render(container) {
    +Section("s1", "About") {
      +"About contents"
    }

    +Section("s1", "Docs") {
      +"Docs contents"
    }
  }
}

class Section(key: Any?, val title: String, val body: WidgetBody) : StatelessWidget(key) {
  override fun render() = Box {
    +Header("title", title)
    +body
  }
}

class Header(key: Any?, val text: String) : StatelessWidget(key) {
  override fun render() = Text(fontSize = 20.0) {
    +text
  }
}
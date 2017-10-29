package react.p01_hello_widgets

import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.dom.render
import cx.ui.framework.widget.StatelessWidget
import kotlin.browser.document

fun main(args: Array<String>) {
  val container = document.getElementById("content")!!

  render(container) {
    +Header("s1", "About")
    +"The contents for about section"

    +Header("s1", "Docs")
    +"The contents for docs section"
  }
}

class Header(key: Any?, val text: String) : StatelessWidget(key) {
  override fun render() = Text(fontSize = 20.0) {
    +text
  }
}
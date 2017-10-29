package react.p00_hello_world

import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {
  val container = document.getElementById("content")!!

  render(container) {
    +Text(fontSize = 20.0) {
      +"Example 1"
    }
    +"Hello, world!"
  }
}
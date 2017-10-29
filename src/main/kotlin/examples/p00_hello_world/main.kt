package examples.p00_hello_world

import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row

fun render() = Column {
  +Row {
    +Text(fontSize = 20.0) {
      +"Example 1"
    }
  }
  +"Hello, world!"
}
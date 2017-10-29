package examples.p01_hello_widgets

import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.ui.framework.widget.StatelessWidget

fun render() = Column {
  +Header("s1", "About")
  +"The contents for about section"

  +Header("s2", "Docs")
  +"The contents for docs section"
}

class Header(key: Any?, val text: String) : StatelessWidget(key) {
  override fun render() = Row {
    +Text(fontSize = 20.0) {
      +text
    }
  }
}
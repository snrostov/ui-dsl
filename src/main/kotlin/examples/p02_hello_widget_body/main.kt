package examples.p02_hello_widget_body

import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.ui.framework.WidgetBody
import cx.ui.framework.widget.StatelessWidget

fun render() = Column {
  +Section("s1", "About") {
    +"About contents"
  }

  +Section("s2", "Docs") {
    +"Docs contents"
  }
}

class Section(key: Any?, val title: String, val body: WidgetBody) : StatelessWidget(key) {
  override fun render() = Box {
    +Header("title", title)
    +body
  }
}

class Header(key: Any?, val text: String) : StatelessWidget(key) {
  override fun render() = Row {
    +Text(fontSize = 20.0) {
      +text
    }
  }
}
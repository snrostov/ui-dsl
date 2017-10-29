package react

import cx.js.ui.framework.widgets.common.layout.Row
import cx.ui.framework.WidgetBody
import cx.ui.framework.widget.StatelessWidget

inline fun MySimpleContainer(
    key: String,
    body: WidgetBody = {}
) = Row {
  +body
}

class MyContainer(
    key: String,
    val body: WidgetBody = {}
) : StatelessWidget(key) {
  override fun render() = Row {
    +body
  }
}
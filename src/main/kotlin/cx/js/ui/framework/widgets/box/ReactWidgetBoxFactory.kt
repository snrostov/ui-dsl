package cx.js.ui.framework.widgets.box

import cx.ui.framework.widget.Widget
import react.ReactElement

interface ReactWidgetBoxFactory {
  fun <W : Widget<S>, S : Any>
      createBox(widget: W): ReactElement
}
package cx.js.ui.framework.widgets.box.std

import cx.ui.framework.widget.Widget


data class StdWidgetBoxProps<W : Widget<*>>(val key: Any?, val widget: W)
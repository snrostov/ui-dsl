package cx.js.ui.framework.widgets.box.std

import cx.ui.framework.widget.BoxedWidget

data class StdWidgetBoxProps<W : BoxedWidget<*, *>>(val key: Any?, val widget: W)
package react

import flutter.rgbColor
import cx.js.ui.framework.widgets.common.ext.ListItem
import cx.js.ui.framework.widgets.common.events.MouseListener
import cx.js.ui.framework.widgets.common.Text

fun SimpleMenuItem(key: String, title: String) = ListItem {
  +Text(color = 0xFF0000.rgbColor) {
    +MouseListener(click = { }) {
      +title
    }
  }
}
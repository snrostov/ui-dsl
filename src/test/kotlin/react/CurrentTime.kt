package react

import cx.js.kotlin.clearInterval
import cx.js.kotlin.setInterval
import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.layout.Row
import cx.ui.framework.widget.BoxedWidget
import cx.ui.framework.widget.WidgetBox
import cx.ui.framework.widget.WidgetBoxData

class CurrentTime(key: Any?) : BoxedWidget<Int, CurrentTime.Box>(key) {
  override val initialState = 0

  override fun initBoxData(box: WidgetBox<Int, *>): Box
      = Box(box)

  class Box(box: WidgetBox<Int, *>) : WidgetBoxData {
    val timerId: Int

    init {
      timerId = setInterval(
          {
            box.updateState {
              it + 1
            }
          }, 1000
      )
    }

    override fun dispose() {
      clearInterval(timerId)
    }
  }

  override fun render(state: Int): RenderObject? = Box(
      width = 100.0
  ) {
    +Row {
      +"count: "
      +"$state"
    }
  }
}
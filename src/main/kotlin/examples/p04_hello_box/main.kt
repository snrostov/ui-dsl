package examples.p04_hello_box

import cx.js.kotlin.*
import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.dom.render
import cx.js.ui.framework.widgets.elements
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import cx.ui.framework.model.Var
import cx.ui.framework.model.widgets.Editor
import cx.ui.framework.widget.Widget
import cx.ui.framework.widget.WidgetBox
import cx.ui.framework.widget.WidgetBoxData
import org.w3c.dom.events.Event
import react.React
import react.element
import kotlin.browser.document

fun render(): RenderObject = elements {
  +Sample()
}!!

class Sample : Widget<Boolean>(null) {
  override val initialState=true

  override fun render(state: Boolean) = Column {
    +Row {
      +"toggle seconds to update widget properties: "

      +Button(onClick = ::toggleSeconds) {
        if (state) {
          +"hide"
        } else {
          +"show"
        }
        +" seconds"
      }
    }

    +Row {
      +"simple clock: "
      +SimpleClock("simple", state)
    }

    +Row {
      +"boxed clock: "
      +BoxedClock("boxed", state)
    }
  }

  private fun toggleSeconds() {
    updateState { !this }
  }
}

data class SimpleClock(
    override val key: Any?,
    val showSeconds: Boolean
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  var timeId: Int? = null

  override fun init() {
    timeId = setInterval(::updateClock, 1000)
    console.log("SimpleClock: timer created")
  }

  fun updateClock() {
    updateState { JsDate() }
  }

  override fun render(state: JsDate) = Box {
    +state.showTime(showSeconds)
  }

  override fun dispose() {
    clearInterval(timeId!!)
    console.log("SimpleClock: timer destroyed")
  }
}

data class BoxedClock(
    override val key: Any?,
    val showSeconds: Boolean
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  override fun initBoxData(box: WidgetBox<JsDate>) = object : WidgetBoxData {
    val timeId = setInterval(::updateClock, 200)

    init {
      console.log("BoxedClock: timer created")
    }

    private fun updateClock() {
      box.updateState { JsDate() }
    }

    override fun dispose() {
      clearInterval(timeId)
      console.log("BoxedClock: timer destroyed")
    }
  }

  override fun render(state: JsDate): RenderObject? = Box {
    +state.showTime(showSeconds)
  }
}

fun JsDate.showTime(showSeconds: Boolean): String {
  return toLocaleTimeString("en-us", dynamicObj {
    it.hour12 = false
    it.hour = "2-digit"
    it.minute = "2-digit"
    if (showSeconds) {
      it.second = "2-digit"
    }
  })
}
package examples.p04_hello_box

import cx.js.kotlin.*
import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.dom.render
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

fun render() = Sample()

class Sample : Widget<JsTimeFormat>(null) {
  override val initialState: JsTimeFormat
    get() = JsTimeFormat()

  override fun render(state: JsTimeFormat) = Column {
    +Row {
      +element("label") {
        +element(
            "input",
            dynamicObj {
              it.type = "checkbox"
              it.onInput = ::setShowSeconds
            }
        )
        +"show seconds"
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

  private fun setShowSeconds(event: Event) {
    updateState {
      copy(second = if (event.checked) "2-digits" else null)
    }
  }
}

class SimpleClock(
    key: Any?,
    val format: JsTimeFormat,
    val locale: String = "en-us"
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

  override fun render(state: JsDate) = Row {
    +state.toLocaleTimeString(locale, format)
  }

  override fun dispose() {
    clearInterval(timeId!!)
    console.log("SimpleClock: timer destroyed")
  }
}

class BoxedClock(
    key: Any?,
    val format: JsTimeFormat,
    val locale: String = "en-us"
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  override fun initBoxData(box: WidgetBox<JsDate>) = object : WidgetBoxData {
    val timeId = setInterval(::updateClock, 1000)

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

  override fun render(state: JsDate): RenderObject? = Row {
    +state.toLocaleTimeString(locale, format)
  }
}

class Clock(
    key: Any?,
    val format: JsTimeFormat,
    val locale: String = "en-us"
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  override fun initBoxData(box: WidgetBox<JsDate>) = object : WidgetBoxData {
    val timeId = setInterval(::updateClock, 1000)

    private fun updateClock() {
      box.updateState { JsDate() }
    }

    override fun dispose() {
      clearInterval(timeId)
    }
  }

  override fun render(state: JsDate): RenderObject? = Row {
    +state.toLocaleTimeString(locale, format)
  }
}
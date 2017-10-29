package examples.p04_hello_box

import cx.js.kotlin.JsDate
import cx.js.kotlin.clearInterval
import cx.js.kotlin.dynamicObj
import cx.js.kotlin.setInterval
import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.common.Box
import cx.js.ui.framework.widgets.common.Text
import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Column
import cx.js.ui.framework.widgets.common.layout.Row
import cx.js.ui.framework.widgets.common.margin
import cx.js.ui.framework.widgets.common.padding
import cx.js.ui.framework.widgets.elements
import cx.ui.framework.widget.Widget
import cx.ui.framework.widget.WidgetBox
import cx.ui.framework.widget.WidgetBoxData
import flutter.Border
import flutter.Color
import react.ReactElement

fun render(): RenderObject = elements {
  +Sample()
}!!

data class State(
    val showSeconds: Boolean = true,
    val showWidgets: Boolean = true,
    val lastMessageId: Int = 0,
    val messages: List<Msg> = listOf()
)

data class Msg(val id: Int, val message: String)

interface Logger {
  fun log(message: String)
}

class Sample : Widget<State>(null), Logger {
  override val initialState = State()

  fun toggleSeconds(state: State) {
    log("seconds will be " + if (!state.showSeconds) "visible" else "invisible")
    updateState {
      copy(showSeconds = !showSeconds)
    }
  }

  fun toggleVisibility(state: State) {
    log("widgets will be " + if (!state.showWidgets) "visible" else "invisible")
    updateState {
      copy(showWidgets = !showWidgets)
    }
  }


  override fun render(state: State): ReactElement {
    return Column {
      +Row {
        +Row {
          +"Toggle seconds to update widget properties (only simple clock should recreate timer)"
        }

        +Row {
          +"Toggle visibility to recreate widgets (both clock will recreate their timers)"
        }

        +Row {
          +Button(onClick = { toggleSeconds(state) }) {
            +(if (state.showSeconds) "hide seconds" else "show seconds")
          }

          +Button(onClick = { toggleVisibility(state) }) {
            +(if (state.showWidgets) "hide widgets" else "show widgets")
          }
        }
      }

      +Box(
          width = 200.0, height = 50.0,
          border = Border(1.0),
          padding = 5.padding,
          margin = 5.margin
      ) {
        if (state.showWidgets) {
          +Row {
            +"simple clock: "
            +SimpleClock("simple", state.showSeconds, this@Sample)
          }

          +Row {
            +"boxed clock: "
            +BoxedClock("boxed", state.showSeconds, this@Sample)
          }
        } else {
          +Row {
            +Text(color = Color(100, 100, 100)) {
              +"(widgets hidden)"
            }
          }
        }
      }

      +Text(fontFamily = "monospace") {
        +Row {
          +Box(
              border = Border(1.0),
              width = 500.0,
              height = 200.0,
              padding = 5.padding,
              margin = 5.margin
          ) {
            +Column {
              state.messages.forEach {
                +Row(it.id) { +it.message }
              }
            }
          }
        }
      }
    }
  }

  override fun log(message: String) {
    updateState {
      val msgId = lastMessageId + 1
      copy(
          messages = messages.takeLast(10) + Msg(msgId, "[$msgId] $message"),
          lastMessageId = msgId
      )
    }
  }
}

data class SimpleClock(
    override val key: Any?,
    val showSeconds: Boolean,
    val logger: Logger
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  var timeId: Int? = null

  override fun init() {
    timeId = setInterval(::updateClock, 200)
    logger.log("SimpleClock: timer created")
  }

  fun updateClock() {
    updateState { JsDate() }
  }

  override fun render(state: JsDate) = Box {
    +state.showTime(showSeconds)
  }

  override fun dispose() {
    clearInterval(timeId!!)
    logger.log("SimpleClock: timer destroyed")
  }
}

data class BoxedClock(
    override val key: Any?,
    val showSeconds: Boolean,
    val logger: Logger
) : Widget<JsDate>(key) {
  override val initialState: JsDate
    get() = JsDate()

  override fun initBoxData(box: WidgetBox<JsDate>) = object : WidgetBoxData {
    val timeId = setInterval(::updateClock, 200)

    init {
      logger.log("BoxedClock: timer created")
    }

    private fun updateClock() {
      box.updateState { JsDate() }
    }

    override fun dispose() {
      clearInterval(timeId)
      logger.log("BoxedClock: timer destroyed")
    }
  }

  override fun render(state: JsDate): RenderObject? = Box {
    +state.showTime(showSeconds)
  }
}

fun JsDate.showTime(showSeconds: Boolean): String {
  return toLocaleTimeString(
      "en-us",
      dynamicObj {
        it.hour12 = false
        it.hour = "2-digit"
        it.minute = "2-digit"
        if (showSeconds) {
          it.second = "2-digit"
        }
      }
  )
}
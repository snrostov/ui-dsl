package cx.ui.framework.widget

import cx.js.ui.framework.widgets.common.actions.Button
import cx.js.ui.framework.widgets.common.layout.Row

class MyCounter(key: String, override val initialState: Int) : Widget<Int>(key) {
  override fun render(state: Int) = Row {
    +Button("inc", onClick = ::inc) {
      +"+1"
    }

    +Button("dec", onClick = ::dec) {
      +"-1"
    }

    +"count: $state"
  }

  private fun inc() {
    updateState {
      this + 1
    }
  }

  private fun dec() {
    updateState {
      this - 1
    }
  }
}
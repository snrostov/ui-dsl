package cx.ui.framework.model.widgets.input

import cx.js.kotlin.dynamicObj
import cx.js.kotlin.inputValue
import cx.ui.framework.model.Var
import cx.ui.framework.model.widgets.Editor
import org.w3c.dom.events.Event
import react.React

class StringInput(
    key: String?,
    value: Var<String>
) : Editor<String>(key, value) {
  override fun render(state: String) = React.createElement(
      "input",
      dynamicObj {
        it.type = "text"
        it.onChange = ::onInputChange
        it.value = state
      }
  )

  fun onInputChange(event: Event) {
    value.update {
      event.inputValue
    }
  }
}
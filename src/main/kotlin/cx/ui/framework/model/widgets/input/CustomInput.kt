package cx.ui.framework.model.widgets.input

import cx.js.kotlin.dynamicObj
import cx.js.kotlin.inputValue
import cx.ui.framework.model.Var
import cx.ui.framework.model.widgets.Observer
import org.w3c.dom.events.Event
import react.React

class CustomInput(
    key: String?,
    val state: Var<State>,
    val inputType: String = "text"
) : Observer<CustomInput.State>(key, state) {
  data class State(val value: String, val valid: Boolean)

  override fun render(state: State) = React.createElement(
      "input",
      dynamicObj {
        if (!state.valid) {
          it.style = dynamicObj {
            it.backgroundColor = "red"
            it.color = "white"
          }
        }
        it.type = inputType
        it.onChange = ::onInputChange
        it.value = state.value
      }
  )

  fun onInputChange(event: Event) {
    state.update {
      State(event.inputValue, true)
    }
  }

  companion object {
    fun int(key: String?, value: Var<Int>): CustomInput {
      return CustomInput(
          key,
          CustomInputTransformer(
              value,
              { it.toString() },
              { it.toIntOrNull() }
          )
      )
    }
  }
}
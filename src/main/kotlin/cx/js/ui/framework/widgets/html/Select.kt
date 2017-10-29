package cx.js.ui.framework.widgets.html

import cx.js.kotlin.dynamicObj
import cx.ui.framework.model.Var
import cx.ui.framework.model.widgets.Editor
import react.element

class Select(
    key: Any?,
    val options: List<Option>,
    value: Var<String>
) : Editor<String>(key, value) {
  data class Option(val value: String, val text: String)

  override fun render(state: String) = element("select") {
    options.forEach { option ->
      +element(
          "option",
          dynamicObj {
            it.value = option.value
            it.selected = state == option.value
          }
      ) {
        +option.text
      }
    }
  }
}
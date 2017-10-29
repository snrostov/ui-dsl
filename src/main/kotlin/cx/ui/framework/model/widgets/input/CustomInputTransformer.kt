package cx.ui.framework.model.widgets.input

import cx.ui.framework.model.ValListener
import cx.ui.framework.model.Var

class CustomInputTransformer<T>(
    private val src: Var<T>,
    private val toString: (T) -> String,
    private val fromString: (String) -> T?
) : Var<CustomInput.State>(
    CustomInput.State(
        toString(src.snapshot),
        true
    )
), ValListener<T> {
  init {
    src.addListener(this)
  }

  var updating = false

  private inline fun <T> doUpdate(actions: () -> T) {
    if (!updating) {
      updating = true
      try {
        actions()
      } finally {
        updating = false
      }
    }
  }

  override fun valueUpdated(value: T, draft: Var<T>) {
    doUpdate {
      this.update {
        CustomInput.State(toString(draft.snapshot), true)
      }
    }
  }

  override fun acceptValue(newVal: CustomInput.State): CustomInput.State {
    doUpdate {
      val result = fromString(newVal.value)
      if (result != null) {
        src.update { result }
        return CustomInput.State(newVal.value, true)
      } else {
        return CustomInput.State(newVal.value, false)
      }
    }
    return newVal
  }
}
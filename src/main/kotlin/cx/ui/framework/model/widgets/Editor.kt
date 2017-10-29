package cx.ui.framework.model.widgets

import cx.ui.framework.model.Var

abstract class Editor<T : Any>(
    key: Any?,
    val value: Var<T>
) : Observer<T>(key, value) {
  fun updateValue(f: T.() -> T) {
    value.update(f)
  }
}
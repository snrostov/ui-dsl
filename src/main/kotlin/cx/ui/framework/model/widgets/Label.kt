package cx.ui.framework.model.widgets

import cx.js.ui.framework.widgets.single
import cx.ui.framework.model.Var

class Label(key: String? = null, draft: Var<String>) : Observer<String>(key, draft) {
  override fun render(state: String) = single {
    +state
  }
}
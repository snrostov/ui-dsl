package cx.ui.framework.model

/**
 * Variable bidirectional binded to other value
 */
class BindedVar<T, U>(
    private val src: Var<T>,
    private val transformTo: (T) -> U,
    private val transformFrom: (U) -> T
) : Var<U>(transformTo(src.snapshot)), ValListener<T> {
  init {
    src.addListener(this)
  }

  var updating = false

  private inline fun doUpdate(actions: () -> Unit) {
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
      update {
        transformTo(value)
      }
    }
  }

  override fun acceptValue(newVal: U): U {
    doUpdate {
      src.update {
        transformFrom(newVal)
      }
    }

    return newVal
  }
}
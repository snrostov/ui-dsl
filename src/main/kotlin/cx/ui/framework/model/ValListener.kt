package cx.ui.framework.model

interface ValListener<T> {
  fun valueUpdated(value: T, draft: Var<T>)
}
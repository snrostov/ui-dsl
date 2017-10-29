package cx.ui.framework.model

/**
 * Listenable value
 */
interface Val<T> {
  val snapshot: T

  fun addListener(listener: ValListener<T>)

  fun removeListener(listener: ValListener<T>)
}
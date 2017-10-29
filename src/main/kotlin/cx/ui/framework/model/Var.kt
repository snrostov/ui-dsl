package cx.ui.framework.model

import kotlin.reflect.KProperty1

/**
 * Listenatable and editable value (variable)
 */
open class Var<T>(initial: T) : Val<T> {
  private var _value = initial

  private val listeners = mutableListOf<ValListener<T>>()

  override val snapshot: T
    get() = _value

  override fun addListener(listener: ValListener<T>) {
    listeners.add(listener)
  }

  override fun removeListener(listener: ValListener<T>) {
    listeners.remove(listener)
  }

  fun update(f: T.() -> T) = update(f, null)

  fun update(f: T.() -> T, updater: ValListener<T>? = null) {
    val newVal = acceptValue(f(_value))
    _value = newVal

    // call back updater first, then all other listeners
    updater?.valueUpdated(newVal, this)
    listeners.forEach {
      if (it != updater) {
        it.valueUpdated(newVal, this)
      }
    }
  }

  open protected fun acceptValue(newVal: T): T = newVal

  operator fun <V> get(kProperty1: KProperty1<T, V>): Var<V> {
    TODO("not implemented")
  }
}
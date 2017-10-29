package cx.ui.framework.widget

import cx.js.ui.framework.widgets.RenderObject

/**
 * Widget without state (acts as function)
 */
abstract class StatelessWidget(key: Any?) : Widget<Unit>(key) {
  override val initialState: Unit
    get() = Unit

  open protected val shouldRender: Boolean
    get() = true

  final override fun shouldRender(currentState: Unit, nextState: Unit): Boolean = shouldRender

  open protected fun beforeRender() {}

  final override fun beforeRender(nextState: Unit) = beforeRender()

  abstract fun render(): RenderObject?

  final override fun render(state: Unit) = render()

  open protected fun afterRender() {}

  final override fun afterRender(prevState: Unit) = afterRender()
}
package cx.ui.framework.widget

import cx.js.ui.framework.widgets.RenderObject

/**
 * User defined composition of [BasicElement]s, that composed depengind on the state [S].
 * State stored outside of component (in [WidgetBox]).
 */
abstract class Widget<S : Any>(open val key: Any?) : RenderObject {
  private lateinit var _box: WidgetBox<S>

  val box: WidgetBox<S>
    get() = _box

  val boxData: WidgetBoxData
    get() = _box.data

  abstract val initialState: S

  open internal fun initBoxData(box: WidgetBox<S>): WidgetBoxData = EmptyWidgetBoxData

  internal fun mount(box: WidgetBox<S>) {
    this._box = box
    init()
  }

  internal fun mountReplacement(
      oldWidget: Widget<S>,
      box: WidgetBox<S>
  ) {
    mount(box)
    updateBoxData(box.data)
  }

  fun updateState(transformState: S.() -> S) {
    _box.updateState(transformState)
  }

  suspend fun waitUpdateState(transformState: S.() -> S) {
    _box.waitUpdateState(transformState)
  }

  /**
   * Вызывается после того как виджет был добавлен
   * в дерево компонентов пользовательского интерфейса
   */
  open protected fun init()
      = Unit

  /**
   * Вызывается после [init], в случае если данный виджет встал
   * на место существующего виджета.
   *
   * В параметр [boxData] передается приложение данного виджета к "коробке",
   * которая хранит его состояние.
   */
  open protected fun updateBoxData(boxData: WidgetBoxData) = Unit

  open internal fun shouldRender(currentState: S, nextState: S): Boolean = nextState != currentState

  open internal fun beforeRender(nextState: S) = Unit

  abstract fun render(state: S): RenderObject?

  open internal fun afterFirstRender() = Unit

  open internal fun afterRender(prevState: S) = Unit

  open internal fun dispose() = Unit

  open internal fun catchError(error: Any?, info: Any?) {

  }
}
package cx.js.ui.framework.widgets.box

import cx.ui.framework.widget.Widget
import cx.ui.framework.widget.WidgetBox
import cx.ui.framework.widget.WidgetBoxData
import react.ReactComponent
import react.ReactOneOrMoreElement
import kotlin.coroutines.experimental.suspendCoroutine

abstract class AbstractWidgetBox<RP, RS, W : Widget<WS>, WS : Any>(
    props: RP,
    context: Any,
    widget: W
) : ReactComponent<RP, RS, Any>(props, context),
    WidgetBox<WS> {
  override var data: WidgetBoxData = widget.initBoxData(this)

  init {
    state = boxState(widget.initialState)
    widget.mount(this)
  }

  var widget = widget
    set(newWidget) {
      val oldWidget = field
      oldWidget.dispose()
      newWidget.mountReplacement(oldWidget, this)
      data.setWidget(newWidget)
      field = newWidget
    }

  abstract val factory: ReactWidgetBoxFactory

  abstract fun boxState(widgetState: WS): RS

  abstract fun unboxState(reactState: RS): WS

  override fun shouldComponentUpdate(nextProps: RP, nextState: RS, nextContext: Any): Boolean
      = widget.shouldRender(unboxState(state), unboxState(nextState))

  override fun componentWillUpdate(nextProps: RP, nextState: RS, nextContext: Any) {
    widget.beforeRender(unboxState(nextState))
  }

  override fun render(): ReactOneOrMoreElement {
    val unboxedState = unboxState(state)
    val widgetsRender = widget.render(unboxedState)
    return when (widgetsRender) {
      is Widget<*> -> factory.createBox(widgetsRender as Widget<Any>)
      else -> widgetsRender.unsafeCast<ReactOneOrMoreElement>()
    }
  }

  override fun componentDidMount() {
    widget.afterFirstRender()
  }

  override fun componentDidUpdate(prevProps: RP, prevState: RS) {
    widget.afterRender(unboxState(prevState))
  }

  override fun componentWillUnmount() {
    data.dispose()
    widget.dispose()
  }

  override fun componentDidCatch(error: Any?, info: Any?) {
    widget.catchError(error, info)
  }

  override fun updateState(transformState: (WS) -> WS) {
    setState { prevState, _ ->
      boxState(transformState(unboxState(prevState)))
    }
  }

  suspend override fun waitUpdateState(transformState: (WS) -> WS) = suspendCoroutine<Unit> {
    setState(
        { prevState, _ ->
          boxState(transformState(unboxState(prevState)))
        },
        {
          it.resume(Unit)
        }
    )
  }
}
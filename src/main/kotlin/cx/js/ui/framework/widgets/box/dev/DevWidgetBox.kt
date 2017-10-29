package cx.js.ui.framework.widgets.box.dev

import cx.js.ui.framework.widgets.box.AbstractWidgetBox
import cx.js.ui.framework.widgets.box.ReactWidgetBoxFactory
import cx.js.ui.framework.widgets.box.State
import cx.ui.framework.widget.Widget
import react.React
import react.ReactElement

/**
 * Компонент React, который пернапрвляет все вызовы в Widget.
 * в React dev tools dev вресия позволяет:
 * - видеть название класса виджета в качетсве displayName
 * - отображает property компонента и позволяет из редактировать
 * - todo: отображать и редактировать состояние
 */
class DevWidgetBox<W : Widget<S>, S : Any> constructor(
    props: dynamic,
    context: Any,
    val widgetFactory: DevWidgetFactory<W, S>
) : AbstractWidgetBox<dynamic, dynamic, W, S>(
    props, context, widgetFactory.createWidget(props)
) {
  override val factory: ReactWidgetBoxFactory
    get() = DevWidgetBox

  companion object : ReactWidgetBoxFactory {
    private val typesCache
        = mutableMapOf<JsClass<*>, DevWidgetFactory<*, *>>()

    override fun <W : Widget<S>, S : Any>
        createBox(widget: W): ReactElement {
      val jsClass = widget::class.js as JsClass<W>
      val factory =
          typesCache.getOrPut(jsClass) {
            DevWidgetFactory(jsClass)
          } as DevWidgetFactory<W, S>

      return React.createElement(
          factory.reactComponentFactory,
          factory.getPops(widget)
      )
    }
  }

  override fun boxState(widgetState: S): State<S> = State(
      widgetState
  )

  override fun unboxState(reactState: State<S>): S = reactState.value

  override fun componentWillReceiveProps(nextProps: dynamic, nextContext: Any) {
    if (!widgetFactory.isPropertiesEquals(props, nextProps)) {
      val oldWidget = widget
      val newWidget = widgetFactory.createWidget(nextProps)
      if (oldWidget != newWidget) {
        widget = newWidget
        props = nextProps
      }
    }
  }
}
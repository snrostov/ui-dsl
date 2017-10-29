package cx.js.ui.framework.widgets.box.std

import cx.js.ui.framework.widgets.box.AbstractWidgetBox
import cx.js.ui.framework.widgets.box.ReactWidgetBoxFactory
import cx.js.ui.framework.widgets.box.State
import cx.ui.framework.widget.Widget
import react.React
import react.ReactElement

class StdWidgetBox<W : Widget<S>, S : Any> private constructor(
    props: StdWidgetBoxProps<W>,
    context: Any
) : AbstractWidgetBox<StdWidgetBoxProps<W>, State<S>, W, S>(props, context, props.widget) {
  override val factory: ReactWidgetBoxFactory
    get() = StdWidgetBox

  companion object : ReactWidgetBoxFactory {
    override fun <W : Widget<S>, S : Any>
        createBox(widget: W): ReactElement = React.createElement(
        StdWidgetBox::class.js,
        StdWidgetBoxProps(widget.key, widget)
    )
  }

  override fun boxState(widgetState: S): State<S> = State(widgetState)

  override fun unboxState(reactState: State<S>): S = reactState.value

  override fun componentWillReceiveProps(nextProps: StdWidgetBoxProps<W>, nextContext: Any) {
    val oldWidget = widget
    val newWidget = nextProps.widget
    if (oldWidget != newWidget) {
      widget = nextProps.widget
      props = nextProps
    }
  }
}
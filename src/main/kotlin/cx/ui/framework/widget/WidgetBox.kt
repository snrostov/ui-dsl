package cx.ui.framework.widget

interface WidgetBox<S> {
  var data: WidgetBoxData;

  fun updateState(transformState: (S) -> S)

  suspend fun waitUpdateState(transformState: (S) -> S)
}

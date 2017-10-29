package cx.ui.framework.model.widgets

import cx.ui.framework.model.Val
import cx.ui.framework.model.ValListener
import cx.ui.framework.model.Var
import cx.ui.framework.widget.Widget
import cx.ui.framework.widget.WidgetBox
import cx.ui.framework.widget.WidgetBoxData

abstract class Observer<T : Any>(
    key: Any?,
    val observable: Val<T>
) : Widget<T>(key) {
  override val initialState: T = observable.snapshot

  /**
   * Приложение к "коробке" виджета.
   * Подписывается на изменения [draft], при каждом обнолвении,
   * заяставляет переририсоваться [Observer].
   *
   * Необходим для того чтобы не передподписываться при измении
   * параметров виджета (в этом случае виджет будет пересоздан,
   * а его коробка - нет)
   */
  override fun initBoxData(box: WidgetBox<T>): WidgetBoxData
      = object : WidgetBoxData, ValListener<T> {
    var observable: Val<T> = this@Observer.observable
      set(new) {
        val old = field
        if (new != old) {
          old.removeListener(this)
          new.addListener(this)
          field = new
        }
      }

    override fun setWidget(widget: Widget<*>) {
      @Suppress("UNCHECKED_CAST")
      observable = (widget as Observer<T>).observable
    }

    override fun valueUpdated(value: T, draft: Var<T>) {
      box.updateState { draft.snapshot }
    }

    override fun dispose() {
      observable.removeListener(this)
    }
  }
}
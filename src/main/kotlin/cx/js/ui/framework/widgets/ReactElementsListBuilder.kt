package cx.js.ui.framework.widgets

import cx.js.ui.framework.widgets.box.ReactWidgetBoxFactory
import cx.js.ui.framework.widgets.box.dev.DevWidgetBox
import cx.ui.framework.WidgetsListBuilder
import cx.ui.framework.widget.Widget
import react.ReactElement
import react.ReactOneOrMoreElement

val factory: ReactWidgetBoxFactory = DevWidgetBox

class ReactElementsListBuilder : WidgetsListBuilder() {
  val list = mutableListOf<ReactElement>()

  val moreThenOne get() = list.size > 1
  val firstOrNull get() = list.firstOrNull()

  override fun add(obj: RenderObject) {
    list.add(obj)
  }

  override fun add(text: String) = add(text.unsafeCast<ReactElement>())

  override fun add(number: Number) = add(number.unsafeCast<ReactElement>())

  override fun <S : Any, W : Widget<S>> add(widget: W)
      = add(factory.createBox(widget))

  val array: Array<RenderObject>
    get()
    = list.toTypedArray()

  val singleOrNull: RenderObject?
    get() = list.singleOrNull()

  @Suppress("IMPLICIT_CAST_TO_ANY")
  inline val asNoneOrMoreElement: ReactOneOrMoreElement?
    get() = when {
      moreThenOne -> array
      else -> firstOrNull
    }.unsafeCast<ReactOneOrMoreElement?>()
}
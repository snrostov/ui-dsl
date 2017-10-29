package cx.js.ui.framework.widgets

import cx.ui.framework.WidgetBody
import react.ReactOneOrMoreElement

@JsName("ElementListBuilder_init")
fun ElementListBuilder_init() = ReactElementsListBuilder()

inline fun ElementBuilder(body: WidgetBody): ReactElementsListBuilder {
  val builder = ReactElementsListBuilder()
  builder.body()
  return builder
}

inline fun single(items: WidgetBody)
    = ElementBuilder(items).singleOrNull!!

inline fun singleOrNull(items: WidgetBody)
    = ElementBuilder(items).singleOrNull

inline fun elementsArray(items: WidgetBody)
    = ElementBuilder(items).array

inline fun elements(items: WidgetBody): ReactOneOrMoreElement?
    = ElementBuilder(items).asNoneOrMoreElement

fun elements(): ReactOneOrMoreElement?
    = null
package cx.js.ui.framework.widgets.common

import cx.js.kotlin.dynamicObj
import cx.js.ui.framework.widgets.RenderObject
import cx.js.ui.framework.widgets.css.css
import cx.js.ui.framework.widgets.css.cssPx
import cx.js.ui.framework.widgets.css.toBackgroundCss
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import cx.ui.framework.widget.StatelessWidget
import flutter.*
import react.React

data class Padding(
    override val left: Double = 0.0,
    override val top: Double = 0.0,
    override val right: Double = 0.0,
    override val bottom: Double = 0.0
) : Insets

val Int.padding
  get() = toDouble().padding

val Double.padding
  get() = Padding(this, this, this, this)

data class Margin(
    override val left: Double = 0.0,
    override val top: Double = 0.0,
    override val right: Double = 0.0,
    override val bottom: Double = 0.0
) : Insets

inline fun Box(
    key: String? = null,
    padding: Padding? = null,
    backgroundColor: Color? = null,
    border: Border? = null,
    borderRadius: BorderRadius? = null,
    boxShadow: BoxShadow? = null,
    boxShadows: List<BoxShadow>? = null,
    gradient: Gradient? = null,
    shape: BoxShape? = null,
    width: Double? = null,
    height: Double? = null,
    minWidth: Double = 0.0,
    maxWidth: Double = Double.POSITIVE_INFINITY,
    minHeight: Double = 0.0,
    maxHeight: Double = Double.POSITIVE_INFINITY,
    margin: Margin? = null,
    transform: Matrix? = null,
    noinline body: WidgetBody = { }
) = Box(
    key,
    padding,
    BoxDecoration(
        backgroundColor,
        border,
        borderRadius,
        join(boxShadow, boxShadows),
        gradient,
        shape
    ),
    BoxConstraints(
        width ?: minWidth, width ?: maxWidth,
        height ?: minHeight, height ?: maxHeight
    ),
    margin, transform, body
)

fun <T : Any> join(item: T?, list: List<T>?): List<T> = if (item == null) {
  list ?: listOf()
} else {
  if (list == null) listOf(item)
  else list + item
}

class Box(
    key: String? = null,
    val padding: Padding? = null,
    val backgroundDecoration: BoxDecoration? = null,
    val constraints: BoxConstraints? = null,
    val margin: Margin? = null,
    val transform: Matrix? = null,
    val body: WidgetBody = { }
) : StatelessWidget(key) {
  override fun render(): RenderObject? {
    val propsStyle = dynamicObj() {
      it.display = "inline-block"
    }
    val props = dynamicObj {
      it.style = propsStyle
    }

    if (padding != null) {
      propsStyle.padding = padding.css
    }

    backgroundDecoration?.toBackgroundCss(propsStyle)

    if (constraints != null) {
      applyConstraints(constraints, propsStyle)
    }

    if (margin != null) {
      propsStyle.margin = margin.css
    }

    if (transform != null) {
      propsStyle.transform = transform.css
    }

    return React.createElement(
        "div",
        props,
        *elementsArray(body)
    )
  }

  private fun applyConstraints(constraints: BoxConstraints, propsStyle: dynamic) {
    if (constraints.minWidth != constraints.maxWidth) {
      if (constraints.maxWidth != Double.POSITIVE_INFINITY || constraints.minWidth != 0.0) {
        TODO("minmax constraints unsupported yet")
      }
      // do nothing
    } else {
      propsStyle.width = constraints.minWidth.cssPx
    }
    if (constraints.minHeight != constraints.maxHeight) {
      if (constraints.maxHeight != Double.POSITIVE_INFINITY || constraints.minHeight != 0.0) {
        TODO("minmax constraints unsupported yet")
      }
      // do nothing
    } else {
      propsStyle.height = constraints.minHeight.cssPx
    }
  }
}
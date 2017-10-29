package cx.ui.framework

import cx.js.ui.framework.widgets.RenderObject
import cx.ui.framework.model.BindedVar
import cx.ui.framework.model.Var
import cx.ui.framework.model.widgets.Label
import cx.ui.framework.widget.StatelessWidget
import cx.ui.framework.widget.Widget

abstract class WidgetsListBuilder {
  abstract fun add(obj: RenderObject)

  abstract fun add(text: String)

  abstract fun add(number: Number)

  abstract fun <S : Any, W : Widget<S>> add(widget: W)

  operator fun <S : Any, W : Widget<S>> W.unaryPlus() = add(this)

  operator fun <A, B> ((A, B) -> RenderObject).unaryPlus(): (Any?, A, B) -> Unit
      = { key, a, b ->
    object : StatelessWidget(key) {
      override fun render(): RenderObject? = this@unaryPlus(a, b)
    }
  }

  operator fun RenderObject.unaryPlus() = add(this@unaryPlus)

  operator fun String.unaryPlus() = add(this)

  operator fun Number.unaryPlus() = add(this)

  inline operator fun WidgetBody.unaryPlus()
      = this@unaryPlus(this@WidgetsListBuilder)

  operator fun Var<String>.unaryPlus() = add(Label(null, this))

  operator fun Var<Int>.unaryPlus() = add(
      Label(
          null,
          BindedVar(
              this, {
            it.toString()
          }, {
            it.toInt()
          }
          )
      )
  )
}
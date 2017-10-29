@file:JsModule("react")
@file:JsNonModule

package react

@JsName("Component")
external abstract class ReactComponent<P, S, C>(props: P, context: C) : ReactElement {
  @JsName("props")
  var props: P

  @JsName("state")
  var state: S

  @JsName("context")
  val context: C

  @get:JsName("getChildContext")
  open val childContext: C

  @JsName("setState")
  fun setState(partial: dynamic)

  @JsName("setState")
  fun setState(partial: dynamic, callback: () -> Unit)

  @JsName("setState")
  fun setState(f: (prevState: S, props: P) -> S)

  @JsName("setState")
  fun setState(f: (prevState: S, props: P) -> S, callback: () -> Unit)

  @JsName("componentWillMount")
  open protected fun componentWillMount()

  @JsName("render")
  abstract protected fun render(): ReactOneOrMoreElement?

  @JsName("componentDidMount")
  open protected fun componentDidMount()

  @JsName("componentWillReceiveProps")
  open protected fun componentWillReceiveProps(nextProps: P, nextContext: C)

  @JsName("shouldComponentUpdate")
  open protected fun shouldComponentUpdate(nextProps: P, nextState: S, nextContext: C): Boolean

  @JsName("componentWillUpdate")
  open protected fun componentWillUpdate(nextProps: P, nextState: S, nextContext: C)

  @JsName("componentDidUpdate")
  open protected fun componentDidUpdate(prevProps: P, prevState: S)

  @JsName("componentWillUnmount")
  open protected fun componentWillUnmount()

  @JsName("componentDidCatch")
  open protected fun componentDidCatch(error: Any?, info: Any?)
}
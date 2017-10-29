package react

@JsModule("react")
@JsNonModule
external object React {
  fun createElement(type: Any, props: dynamic, vararg child: ReactElement?): ReactElement
}

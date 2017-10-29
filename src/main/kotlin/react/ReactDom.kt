package react

import org.w3c.dom.Element

@JsModule("react-dom")
@JsNonModule
external object ReactDom {
  fun render(
      element: ReactOneOrMoreElement?,
      container: Element?,
      callback: () -> Unit = definedExternally
  )

  fun hydrate(
      element: dynamic,
      container: Element?,
      callback: () -> Unit = definedExternally
  )

  fun unmountComponentAtNode(domContainerNode: Element?)

  fun findDOMNode(component: dynamic): Element

  fun createPortal(element: dynamic, container: Element?): ReactElement
}
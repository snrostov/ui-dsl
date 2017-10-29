package cx.js.ui.framework.widgets.dom

import cx.js.ui.framework.widgets.ElementBuilder
import cx.ui.framework.WidgetBody
import org.w3c.dom.Element
import react.ReactDom

fun render(container: Element, body: WidgetBody)
    = ReactDom.render(ElementBuilder(body).asNoneOrMoreElement, container)
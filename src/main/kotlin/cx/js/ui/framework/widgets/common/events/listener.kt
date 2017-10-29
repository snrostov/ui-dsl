package cx.js.ui.framework.widgets.common.events

import cx.ui.framework.WidgetBody
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import react.ReactElement

typealias EventListener<E> = (E) -> Unit

fun MouseListener(
    click: EventListener<MouseEvent>? = null,
    doubleClick: EventListener<MouseEvent>? = null,
    mouseDown: EventListener<MouseEvent>? = null,
    mouseEnter: EventListener<MouseEvent>? = null,
    mouseLeave: EventListener<MouseEvent>? = null,
    mouseMove: EventListener<MouseEvent>? = null,
    mouseOver: EventListener<MouseEvent>? = null,
    mouseOut: EventListener<MouseEvent>? = null,
    mouseUp: EventListener<MouseEvent>? = null,
    contextMenu: EventListener<MouseEvent>? = null,
    items: WidgetBody = {}
): ReactElement = TODO()

fun KeyListener(
    down: EventListener<KeyboardEvent>? = null,
    press: EventListener<KeyboardEvent>? = null,
    up: EventListener<KeyboardEvent>? = null,
    items: WidgetBody = {}
): ReactElement = TODO()
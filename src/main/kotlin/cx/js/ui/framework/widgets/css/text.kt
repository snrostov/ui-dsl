package cx.js.ui.framework.widgets.css

import flutter.TextAlign
import flutter.TextDecoration
import flutter.TextOverflow
import flutter.TextStyle

fun TextStyle.toReactProps(props: dynamic) {
  TODO("not implemented")
}


fun TextAlign.toReactProps(props: dynamic) {
  props.textAlign = when (this) {
    TextAlign.left -> "left"
    TextAlign.right -> "right"
    TextAlign.center -> "center"
    TextAlign.justify -> "justify"
    TextAlign.start -> TODO()
    TextAlign.end -> TODO()
  }
}


fun TextDecoration.toReactProps(props: dynamic) {
  TODO("not implemented")
}

fun TextOverflow.toReactProps(props: dynamic) {
  TODO("not implemented")
}


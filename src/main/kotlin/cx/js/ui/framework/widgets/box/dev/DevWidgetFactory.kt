package cx.js.ui.framework.widgets.box.dev

import cx.js.kotlin.buildProps
import cx.js.kotlin.parameterNames
import cx.ui.framework.widget.Widget

class DevWidgetFactory<W : Widget<S>, S : Any>(val jsClass: JsClass<W>) {
  val propNames = jsClass.parameterNames

  val reactComponentFactory
      = { props: dynamic, context: Any ->
    DevWidgetBox(props, context, this)
  }

  init {
    reactComponentFactory.unsafeCast<dynamic>().displayName = jsClass.name
  }

  fun getPops(obj: W): dynamic = buildProps { result ->
    val props = this
    propNames.forEach {
      val value = obj.asDynamic()[it]
      check(value !== undefined) { "property value '$it' is missing in $obj" }
      result[it] = value

      // http://facebook.github.io/react/blog/2014/10/16/react-v0.12-rc1.html#breaking-change-key-and-ref-removed-from-this.props
      // You can no longer access this.props.ref and this.props.key from inside the Component instance itself. So you need to use a different name for those props.
      if (it == "key") {
        result["_key"] = value
      }
    }
  }

  fun createWidget(props: dynamic): W {
    @Suppress("UNUSED_VARIABLE")
    val ctor = jsClass

    val args = mutableListOf<dynamic>()
    args.add(null)

    propNames.forEach {
      // http://facebook.github.io/react/blog/2014/10/16/react-v0.12-rc1.html#breaking-change-key-and-ref-removed-from-this.props
      // You can no longer access this.props.ref and this.props.key from inside the Component instance itself. So you need to use a different name for those props.
      val value = if (it == "key") props["_key"] else props[it]
      check(value !== undefined) { "property value '$it' is missing in $props" }
      args.add(value)
    }

    @Suppress("UNUSED_VARIABLE")
    val argsArray = args.toTypedArray()

    //language=JavaScript 1.6
    return js("new (Function.prototype.bind.apply(ctor, argsArray))").unsafeCast<W>()
  }

  fun isPropertiesEquals(a: dynamic, b: dynamic): Boolean {
    propNames.forEach {
      if (a[it] != b[it]) return false
    }
    return true
  }
}
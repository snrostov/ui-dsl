package cx.js.kotlin

import kotlin.reflect.KClass

fun <T : Any> KClass<T>.createInstance(): T {
  @Suppress("UNUSED_VARIABLE")
  val ctor = this.js
  return js("new ctor()")
}

fun <T : Any> JsClass<T>.createInstance(vararg args: dynamic): T {
  @Suppress("UNUSED_VARIABLE")
  val ctor = this

  @Suppress("UNUSED_VARIABLE")
  val argsArray = (listOf(null) + args).toTypedArray()

  //language=JavaScript 1.6
  return js("new (Function.prototype.bind.apply(ctor, argsArray))").unsafeCast<T>()
}

val JsClass<*>.parameterNames: List<String>
  get() {
    val body = this.toString()
//    check(body.startsWith("function ("))
    val argsStart = body.indexOf('(')
    val argsString = body.substring(
        argsStart + 1,
        body.indexOf(')', argsStart)
    )
    return argsString.split(',').map { it.trim() }
  }
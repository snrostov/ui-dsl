package cx.js.kotlin

inline fun <T> buildProps(builder: (T) -> Unit = {}): T {
  val obj: T = js("({})").unsafeCast<T>()
  builder(obj)
  return obj
}

inline fun dynamicObj(builder: (dynamic) -> Unit = {}): dynamic = buildProps(builder)

fun Any.getOwnPropertyNames(): Array<String> {
  val me = this
  //language=JavaScript
  return js("Object.getOwnPropertyNames(me)") as Array<String>
}
package cx.js.kotlin

external interface ITimeFormat {
//  val timeZone: String?
//  val hour12: Boolean
  val hour: String?
  val minute: String?
  val second: String?
//  val timeZoneName: String?
}

data class JsTimeFormat(
//    override val timeZone: String? = null,
//    override val hour12: Boolean = false,
    override val hour: String? = "2-digit",
    override val minute: String? = "2-digit",
    override val second: String? = "2-digit"//,
//    override val timeZoneName: String? = null
) : ITimeFormat

@JsName("Date")
external class JsDate {
  constructor()

  constructor(s: String)

  fun toLocaleTimeString(locale: String, options: dynamic): String
}
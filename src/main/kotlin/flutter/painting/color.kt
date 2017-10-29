package flutter

data class Color(val r: Int, val g: Int, val b: Int, val a: Int = 1) {
  val value: Long
    get() = a.toLong().and(0xff).shl(24)
        .or(r.toLong().and(0xff).shl(16))
        .or(g.toLong().and(0xff).shl(8))
        .or(b.toLong().and(0xff))

  val hex: String = "#${r.byteHex}${g.byteHex}${b.byteHex}${a.byteHex}"
}

private val hexChars = "0123456789ABCDEF"
val Int.byteHex
  get() = "${hexChars[this / 255]}${hexChars[this % 255]}"

val Int.rgbColor
  get() = Color(this.toLong().shr(8))

val Int.rgbaColor
  get() = Color(this.toLong().ushr(8))

fun Color(value: Long): Color {
  check(value in 0x01000000..0xFFFFFFFF)
  return Color(
      0x00ff0000.and(value.toInt()).shr(16),
      0x0000ff00.and(value.toInt()).shr(8),
      0x000000ff.and(value.toInt()).shr(0),
      0xff000000.and(value).shr(24).toInt()
  )
}

fun Color(r: Int, g: Int, b: Int, opacity: Double)
    = Color(r, g, b, (opacity * 0xFF).toInt())

fun Color.copy(
    r: Int = this.r,
    g: Int = this.g,
    b: Int = this.b,
    opacity: Double = Double.NaN
) = Color(
    r, g, b,
    if (opacity == Double.NaN) a else (opacity * 0xFF).toInt()
)

val black = Color(0, 0, 0, 255)
val white = Color(255, 255, 255, 255)
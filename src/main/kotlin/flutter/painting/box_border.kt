package flutter

data class Border(
    val top: BorderSide,
    val right: BorderSide,
    val bottom: BorderSide,
    val left: BorderSide
)

fun Border(side: BorderSide = BorderSide())
    = Border(side, side, side, side)

val BorderSide.allSides
  get() = Border(this)

fun Border(
    width: Double = 1.0,
    style: BorderStyle = BorderStyle.solid,
    color: Color = black
)
    = Border(BorderSide(color, width, style))

enum class BoxShape {
  rectangle, cricle
}
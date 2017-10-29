package flutter

data class BorderSide(
    val color: Color = black,
    val width: Double = 1.0,
    val style: BorderStyle = BorderStyle.solid
)

enum class BorderStyle {
  none, solid
}
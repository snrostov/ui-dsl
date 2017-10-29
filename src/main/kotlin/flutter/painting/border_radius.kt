package flutter

data class BorderRadius(
    val topLeft: Radius = 0.radius,
    val topRight: Radius = 0.radius,
    val bottomLeft: Radius = 0.radius,
    val bottomRight: Radius = 0.radius
) {
  val cornersSymmetrical
      = topLeft.symmetrical
      && topRight.symmetrical
      && bottomLeft.symmetrical
      && bottomRight.symmetrical
}

fun BorderRadius(radius: Radius)
    = BorderRadius(radius, radius, radius, radius)

inline val Radius.allCorners get() = BorderRadius(this)
inline val Double.allCorners get() = BorderRadius(radius)
inline val Int.allCorners get() = BorderRadius(radius)
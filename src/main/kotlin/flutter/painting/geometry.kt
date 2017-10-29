package flutter

data class Radius(val x: Double, val y: Double) {
  val symmetrical
    get() = x == y
}

inline val Double.radius get() = Radius(this, this)
inline val Int.radius get() = toDouble().radius

data class Offset(val dx: Double, val dy: Double)

inline val Double.offset get() = Offset(this, this)
inline val Int.offset get() = toDouble().offset

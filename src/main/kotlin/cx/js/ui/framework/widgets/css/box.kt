package cx.js.ui.framework.widgets.css

import flutter.*
import flutter.BorderStyle.none
import flutter.BorderStyle.solid
import org.khronos.webgl.get
import kotlin.math.floor

fun BoxDecoration.toBackgroundCss(styles: dynamic) {
  if (borderRadius != null) {
    styles.borderRadius = borderRadius.css
  }
  if (color != null) {
    styles.background = color.css
  }

  border?.toCss(styles)
}

fun Border.toCss(styles: dynamic) {
  if (left == top && left == right && left == bottom) {
    if (left.style != none) {
      styles.border = left.css
    }
  } else {
    if (left.style != none) styles.borderLeft = left.css
    if (right.style != none) styles.borderLeft = right.css
    if (top.style != none) styles.borderLeft = top.css
    if (bottom.style != none) styles.borderLeft = bottom.css
  }
}

val BorderRadius.css: String
  get() {
    return if (cornersSymmetrical) {
      borderRadiusAxisCss(topLeft.x, topRight.x, bottomLeft.x, bottomRight.x)
    } else {
      borderRadiusAxisCss(topLeft.x, topRight.x, bottomLeft.x, bottomRight.x) + " / " +
          borderRadiusAxisCss(topLeft.y, topRight.y, bottomLeft.y, bottomRight.y)
    }
  }

fun borderRadiusAxisCss(
    topLeft: Double,
    topRight: Double,
    bottomLeft: Double,
    bottomRight: Double
): String {
  val tlbr = topLeft == bottomRight
  val trbl = topRight == bottomLeft
  return if (tlbr && trbl) {
    if (topLeft == topRight) topLeft.cssPx // "all sides" shorthand
    else "${topLeft.cssPx} ${topRight.cssPx}" // shorthand
  } else "${topLeft.cssPx} ${topRight.cssPx} ${bottomRight.cssPx} ${bottomLeft.cssPx}"
}

val Radius.css: String
  get() = if (x == y) x.cssPx else "${x.cssPx} / ${y.cssPx}"

val BorderSide.css: String
  get() = "${width}px ${style.css} ${color.css}"

val BorderStyle.css: String
  get() = when (this) {
    none -> "none"
    solid -> "solid"
  }

val Color.css: String
  get() = if (a == 255) {
    "#${r.byteHex}${g.byteHex}${b.byteHex}"
  } else {
    "rgb($r, $g, $b, ${a.toDouble() / 255})"
  }

val Double.cssPx: String
  get() {
    check(isFinite())
    return when (this) {
      0.0 -> "0"
      floor(this) -> "${this.toInt()}px"
      else -> "${this}px"
    }
  }

val Insets.css: String
  get() {
    val lr = left == right
    val tb = top == bottom
    return if (tb && lr) {
      if (left == top) left.cssPx // "all sides" shorthand
      else "${top.cssPx} ${left.cssPx}" // "left-right top-bottom" shorthand
    } else "${top.cssPx} ${right.cssPx} ${bottom.cssPx} ${left.cssPx}"
  }

val Matrix.css: String
  get() = if (array.length == 5) "matrix($css2D)" else "matrix3d($css3D)"

val Matrix.css2D: String
  get() = "${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ${array[5]}"

val Matrix.css3D: String
  get() = "${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, " +
      "${array[4]}, ${array[5]}, ${array[6]}, ${array[7]}, " +
      "${array[8]}, ${array[9]}, ${array[10]}, ${array[11]}, " +
      "${array[12]}, ${array[13]}, ${array[14]}, ${array[15]}"
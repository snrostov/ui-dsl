package flutter

/**
 * Base class for [Alignment] that allows for text-direction aware
 * resolution.
 *
 * A property or argument of this type accepts classes created either with [new
 * Alignment] and its variants, or [new AlignmentDirectional].
 *
 * To convert a [AlignmentGeometry] object of indeterminate type into a
 * [Alignment] object, call the [resolve] method.
 */
abstract class AlignmentGeometry

/**
 * A point within a rectangle.
 *
 * `Alignment(0.0, 0.0)` represents the center of the rectangle. The distance
 * from -1.0 to +1.0 is the distance from one side of the rectangle to the
 * other side of the rectangle. Therefore, 2.0 units horizontally (or
 * vertically) is equivalent to the width (or height) of the rectangle.
 *
 * `Alignment(-1.0, -1.0)` represents the top left of the rectangle.
 *
 * `Alignment(1.0, 1.0)` represents the bottom right of the rectangle.
 *
 * `Alignment(0.0, 3.0)` represents a point that is horizontally centered with
 * respect to the rectangle and vertically below the bottom of the rectangle by
 * the height of the rectangle.
 *
 * [Alignment] use visual coordinates, which means increasing [x] moves the
 * point from left to right. To support layouts with a right-to-left
 * [TextDirection], consider using [AlignmentDirectional], in which the
 * direction the point moves when increasing the horizontal value depends on
 * the [TextDirection].
 *
 * A variety of widgets use [Alignment] in their configuration, most
 * notably:
 *
 *  * [Align] positions a child according to a [Alignment].
 *
 * See also:
 *
 *  * [AlignmentDirectional], which has a horizontal coordinate orientation
 *    that depends on the [TextDirection].
 *  * [AlignmentGeometry], which is an abstract type that is agnostic as to
 *    whether the horizontal direction depends on the [TextDirection].
 */
data class Alignment(val x: Double, val y: Double) : AlignmentGeometry()

/// The top left corner.
val topLeft = Alignment(-1.0, -1.0);

/// The center point along the top edge.
val topCenter = Alignment(0.0, -1.0);

/// The top right corner.
val topRight = Alignment(1.0, -1.0);

/// The center point along the left edge.
val centerLeft = Alignment(-1.0, 0.0);

/// The center point, both horizontally and vertically.
val center = Alignment(0.0, 0.0);

/// The center point along the right edge.
val centerRight = Alignment(1.0, 0.0);

/// The bottom left corner.
val bottomLeft = Alignment(-1.0, 1.0);

/// The center point along the bottom edge.
val bottomCenter = Alignment(0.0, 1.0);

/// The bottom right corner.
val bottomRight = Alignment(1.0, 1.0);
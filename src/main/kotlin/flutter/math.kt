package flutter

import org.khronos.webgl.Float32Array

/// 4D Matrix.
/// Values are stored in addColumn major order.
data class Matrix(val array: Float32Array) {
  companion object {
    val id = Matrix4(
        1f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 0f, 0f, 1f
    )
  }

  fun translate(x: Double, y: Double = 0.0, z: Double = 0.0): Matrix {
    TODO()
  }

  fun rotateZ(angle: Double): Matrix {
    TODO()
  }

  fun scale(x: Double = 1.0, y: Double = 1.0, z: Double = 1.0): Matrix {
    TODO()
  }
}

inline fun translate(x: Float, y: Float, z: Float = 0.0f): Matrix
    = Matrix4(
    1f, 0f, 0f, 0f,
    0f, 1f, 0f, 0f,
    0f, 0f, 1f, 0f,
    x, y, z, 1f
)

inline fun rotateZ(angle: Double): Matrix
    = Matrix.id.rotateZ(angle)

inline fun scale(x: Float = 1f, y: Float = 1f, z: Float = 1f): Matrix
    = Matrix4(
    x, 0f, 0f, 0f,
    0f, y, 0f, 0f,
    0f, 0f, z, 0f,
    0f, 0f, 0f, 1f
)


inline fun Matrix4(
    a00: Float,
    a01: Float,
    a02: Float,
    a03: Float,
    a10: Float,
    a11: Float,
    a12: Float,
    a13: Float,
    a20: Float,
    a21: Float,
    a22: Float,
    a23: Float,
    a30: Float,
    a31: Float,
    a32: Float,
    a33: Float
) = Matrix(
    Float32Array(
        arrayOf(
            a00, a01, a02, a03,
            a10, a11, a12, a13,
            a20, a21, a22, a23,
            a30, a31, a32, a33
        )
    )
)
package flutter

data class BoxConstraints(
    val minWidth: Double = 0.0,
    val maxWidth: Double = Double.POSITIVE_INFINITY,
    val minHeight: Double = 0.0,
    val maxHeight: Double = Double.POSITIVE_INFINITY
)
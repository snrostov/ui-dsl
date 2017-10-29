package flutter

data class BoxDecoration(
    val color: Color? = null,
    val border: Border? = null,
    val borderRadius: BorderRadius? = null,
    val boxShadow: List<BoxShadow> = listOf(),
    val gradient: Gradient? = null,
    val shape: BoxShape? = null
)
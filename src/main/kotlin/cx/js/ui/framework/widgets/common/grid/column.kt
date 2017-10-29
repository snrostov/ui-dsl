package cx.js.ui.framework.widgets.common.grid

interface GridColumnsSpawn : ClosedRange<GridColumn>

data class GridFractionalTrackSize(
    val fraction: Double
)

data class GridMinMaxTrackSize(
    val min: Double,
    val max: Double
)

class GridColumn(
    val index: Int,
    val size: GridMinMaxTrackSize
) : Comparable<GridColumn>, GridColumnsSpawn {
  override fun compareTo(other: GridColumn): Int = index.compareTo(other.index)

  override val start: GridColumn
    get() = this

  override val endInclusive: GridColumn
    get() = this
}

class GridColumnsInterval(
    override val start: GridColumn,
    override val endInclusive: GridColumn
) : GridColumnsSpawn

operator fun GridColumn.rangeTo(other: GridColumn)
    = GridColumnsInterval(this, other)
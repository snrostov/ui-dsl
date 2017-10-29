package cx.js.ui.framework.widgets.common.grid

interface GridRowsSpawn : ClosedRange<GridRow>

class GridRow(
    val index: Int
) : Comparable<GridRow>, GridRowsSpawn {
  override fun compareTo(other: GridRow): Int = index.compareTo(other.index)

  override val start: GridRow
    get() = this

  override val endInclusive: GridRow
    get() = this
}

class GridRowInterval(
    override val start: GridRow,
    override val endInclusive: GridRow
) : GridRowsSpawn

operator fun GridRow.rangeTo(other: GridRow)
    = GridRowInterval(this, other)
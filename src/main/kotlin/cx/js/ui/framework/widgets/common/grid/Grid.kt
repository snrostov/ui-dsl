package cx.js.ui.framework.widgets.common.grid

import cx.ui.framework.WidgetBody

class Grid(
    val columns: List<GridColumn>,
    val rows: List<GridRow>,
    val items: List<GridItem>
)

class GridItem(
    val columns: GridColumnsSpawn,
    val rows: GridRowsSpawn,
    val children: WidgetBody
)

fun Grid(body: GridBuilder.() -> Unit)
    = GridBuilder().apply(body)

class GridBuilder {
  val columns = mutableListOf<GridColumn>()
  val rows = mutableListOf<GridRow>()
  val items = mutableListOf<GridItem>()

  fun addColumn(
      size: GridMinMaxTrackSize = GridMinMaxTrackSize(Double.MIN_VALUE, Double.MAX_VALUE),
      body: InGridColumnsTrack.() -> Unit = {}
  ): GridColumn
      = GridColumn(columns.size, size)

  fun addRow(body: InGridRowsTrack.() -> Unit = {}): GridRow = GridRow(rows.size)

  operator fun GridItem.unaryPlus() = this
}

class InGridRowsTrack(val row: GridRowsSpawn) {
  fun addItem(column: GridColumnsSpawn, body: WidgetBody)
      = GridItem(column, row, body)
}

class InGridColumnsTrack(val row: GridRowsSpawn) {
  fun addItem(column: GridColumnsSpawn, body: WidgetBody)
      = GridItem(column, row, body)
}
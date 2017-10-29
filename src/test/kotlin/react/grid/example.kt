package react.grid

import cx.js.ui.framework.widgets.common.grid.Grid
import cx.js.ui.framework.widgets.common.grid.GridItem
import cx.js.ui.framework.widgets.common.grid.rangeTo

fun main(args: Array<String>) {
  Grid {
    val labels = addColumn()
    val inputs = addColumn()

    addRow {
      addItem(labels) {

      }

      addItem(inputs) {

      }
    }

    val row2 = addRow()
    val row3 = addRow()

    +GridItem(columns = labels..inputs, rows = row2..row3) {

    }

    +GridItem(columns = columns[3]..columns[6], rows = rows[4]..rows[5]) {

    }
  }
}
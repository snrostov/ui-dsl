//package cx.ui.framework.model
//
//import react.ReactElement
//import cx.js.ui.framework.widgets.common.actions.Button
//import cx.js.ui.framework.widgets.common.ext.Input
//import cx.js.ui.framework.widgets.common.ext.text
//import cx.js.ui.framework.widgets.common.layout.Column
//import cx.js.ui.framework.widgets.common.layout.Row
//
//class StringEditor(
//    key: String,
//    draft: Var<String>
//) : ValueEditor<String>(key, draft) {
//  override fun render(draft: Var<String>)
//      = Input(
//      type = text,
//      value = draft.value,
//      onChange = ::change
//  )
//
//  private fun change(s: String)
//      = updateValue { s }
//}
//
//class IntEditor(
//    key: String,
//    draft: Var<Int>
//) : ValueEditor<Int>(key, draft) {
//  override fun render(draft: Var<Int>) = Row {
//    +Button(onClick = ::dec) {
//      +"dec"
//    }
//    +"${draft.value}"
//    +Button(onClick = ::inc) {
//      +"inc"
//    }
//  }
//
//  private fun inc() {
//    updateValue { this + 1 }
//  }
//
//  private fun dec() {
//    updateValue { this - 1 }
//  }
//}
//
//class IntsListEditor(
//    key: String,
//    draft: ListDraft<Int>
//) : ListEditor<Int>(key, draft) {
//  override fun render(draft: ListDraft<Int>): ReactElement = Column {
//    +Row {
//      +"size: "
//      +IntEditor("size", draft.size)
//    }
//    +Row {
//      +"items:"
//    }
//    +Row {
//      draft.forEachIndexed { index, item ->
//        +IntEditor(index.toString(), item)
//      }
//    }
//  }
//}
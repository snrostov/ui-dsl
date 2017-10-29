package cx.ui.framework.model

fun <A, B> Val<A>.map(transform: (A) -> B): Val<B> {
  val result = Var(transform(snapshot))
  addListener(
      object : ValListener<A> {
        override fun valueUpdated(value: A, draft: Var<A>) {
          result.update {
            transform(value)
          }
        }
      }
  )
  return result
}
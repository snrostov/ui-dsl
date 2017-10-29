package react

@DslMarker
annotation class ReactDsl

/**
 * Marker type class that describes the return type of [ReactComponent.render]
 *
 * The [ReactElement], set of [ReactElement]s
 **/
@ReactDsl
external interface ReactOneOrMoreElement

/**
 * Marker type class that describes type for [React.createElement] `child` parameter.
 *
 * The [ReactElement] that can be passed to [React.createElement] `child` parameter.
 *
 * Elements created using the [elements] DSL function.
 *
 * Example:
 * ```
 *    elements {
 *        +"text"
 *        +div {
 *          +"text in div"
 *        }
 *        +123
 *    }
 * ```
 *
 * Can be one of:
 * - **Basic elements.** Basic element provided by platform. For HTML DOM it is `<div>`, `<p>`, etc. See [BasicElement].
 * - **Widgets.** User defined components. See [Widget].
 * - **String and numbers.** Rendered as text DOM nodes.
 * - **Portals.** Created with ReactDOM.createPortal.
 **/
external interface ReactElement : ReactOneOrMoreElement

/**
 * Basic element.
 *
 * Html tags: `<div>`, `<p>`, etc.
 * Use DSL functions [div], [p], etc to create corresponding basic elements.
 */
external class BasicElement : ReactElement
package cx.js.kotlin

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event

internal val Event.inputValue: String
  get() = (target as? HTMLInputElement)?.value ?: (target as? HTMLTextAreaElement)?.value ?: ""


internal val Event.checked: Boolean
  get() = (target as? HTMLInputElement)?.checked ?: false


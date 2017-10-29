package cx.js.kotlin

external fun setInterval(callback: () -> Unit, interval: Int): Int

external fun clearInterval(timerId: Int)
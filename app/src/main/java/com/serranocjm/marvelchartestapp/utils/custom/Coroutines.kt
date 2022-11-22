package com.serranocjm.marvelchartestapp.utils.custom

import kotlinx.coroutines.* // ktlint-disable no-wildcard-imports

/**
 * Custom coroutines object to avoid code-verbosity
 */
object Coroutines {
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    @OptIn(DelicateCoroutinesApi::class)
    fun globalIo(work: suspend (() -> Unit)) =
        GlobalScope.launch(Dispatchers.IO) {
            work()
        }

    fun lifecycle(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main.immediate)
}

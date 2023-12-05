package executor

import kotlinx.coroutines.CoroutineDispatcher

expect class MainDispatcher() {
    val dispatcher: CoroutineDispatcher
}
package cz.janjanovec.socialhubkmp.viewModel

import executor.MainIoExecutor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState> : MainIoExecutor() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract suspend fun handleEvent(event: Event)

    fun setEvent(event: Event) {
        val newEvent = event
        launch { _event.emit(newEvent) }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}
package cz.janjanovec.socialhubkmp.viewModel.global.settings

import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel
import cz.janjanovec.socialhubkmp.viewModel.BasicUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class GlobalSettingsViewModel: BaseViewModel<GlobalSettingsContract.Event, GlobalSettingsContract.State>() {
    override fun createInitialState(): GlobalSettingsContract.State =
        GlobalSettingsContract.State(0.0f, BasicUiState.Loading)

    init {
        launch {
            delay(1000)
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            delay(1000)
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
            setState { copy(loadingProgress = currentState.loadingProgress + 0.1f) }
        }
    }

    val userLoggedIn: Boolean
        get() = currentState.account != null
    override suspend fun handleEvent(event: GlobalSettingsContract.Event) {
        TODO("Not yet implemented")
    }
}
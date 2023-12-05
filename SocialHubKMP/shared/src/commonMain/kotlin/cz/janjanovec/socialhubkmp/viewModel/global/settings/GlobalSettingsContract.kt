package cz.janjanovec.socialhubkmp.viewModel.global.settings

import cz.janjanovec.socialhubkmp.utils.Logger
import cz.janjanovec.socialhubkmp.viewModel.BasicUiState
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface GlobalSettingsContract {
    data class State(
        val loadingProgress: Float,
        val account: BasicUiState<Logger?>
    ): UiState
    sealed interface Event: UiEvent {

    }
}
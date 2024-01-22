package cz.janjanovec.socialhubkmp.viewModel.auth.additionalAccountData

import cz.janjanovec.socialhubkmp.utils.AdditionalAccountData
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface AdditionalAccountDataContract {
    data class State(
        val stack: MutableList<AdditionalAccountData>,
        val currentStep: AdditionalAccountData?
    ): UiState
    sealed interface Event: UiEvent {
        object NextStep: Event
    }
}
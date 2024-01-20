package cz.janjanovec.socialhubkmp.viewModel.auth.additionalAccountData

import cz.janjanovec.socialhubkmp.utils.AdditionalAccountData
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel

open class AdditionalAccountDataViewModel(
    val additionalAccountData: List<AdditionalAccountData>
): BaseViewModel<AdditionalAccountDataContract.Event, AdditionalAccountDataContract.State>() {
    override fun createInitialState(): AdditionalAccountDataContract.State {
        return AdditionalAccountDataContract.State(
            emptyList<AdditionalAccountData>().toMutableList()
        )
    }

    init {
        setState {
            copy(
                additionalAccountData.toMutableList()
            )
        }
    }

    val currentStep: AdditionalAccountData?
        get() = additionalAccountData.lastOrNull()

    override suspend fun handleEvent(event: AdditionalAccountDataContract.Event) {
        when (event)  {
            is AdditionalAccountDataContract.Event.NextStep -> nextStep()
        }
    }

    private suspend fun nextStep() {
        if (additionalAccountData.isNotEmpty()) {
            setState {
                copy(
                    additionalAccountData.dropLast(1).toMutableList()
                )
            }
        }
    }
}
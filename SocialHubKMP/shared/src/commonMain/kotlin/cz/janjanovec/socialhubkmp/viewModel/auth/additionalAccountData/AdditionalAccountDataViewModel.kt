package cz.janjanovec.socialhubkmp.viewModel.auth.additionalAccountData

import cz.janjanovec.socialhubkmp.utils.AdditionalAccountData
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel

open class AdditionalAccountDataViewModel(
    val additionalAccountData: List<AdditionalAccountData>
): BaseViewModel<AdditionalAccountDataContract.Event, AdditionalAccountDataContract.State>() {
    override fun createInitialState(): AdditionalAccountDataContract.State {
        return AdditionalAccountDataContract.State(
            emptyList<AdditionalAccountData>().toMutableList(),
            null
        )
    }

    init {
        setState {
            copy(
                additionalAccountData.toMutableList(),
                additionalAccountData.lastOrNull()
            )
        }
    }

    override suspend fun handleEvent(event: AdditionalAccountDataContract.Event) {
        when (event)  {
            is AdditionalAccountDataContract.Event.NextStep -> nextStep()
        }
    }

    private suspend fun nextStep() {
        if (additionalAccountData.isNotEmpty()) {
            val newData = additionalAccountData.dropLast(1).toMutableList()
            setState {
                copy(
                    stack = newData,
                    currentStep = newData.lastOrNull()
                )
            }
        }
    }
}
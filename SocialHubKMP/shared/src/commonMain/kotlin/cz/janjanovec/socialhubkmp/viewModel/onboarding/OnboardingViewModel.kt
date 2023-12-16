package cz.janjanovec.socialhubkmp.viewModel.onboarding

import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel

open class OnboardingViewModel: BaseViewModel<OnboardingContract.Event, OnboardingContract.State>() {
    override fun createInitialState(): OnboardingContract.State = OnboardingContract.State(
        OnboardingContract.OnboardingStep.WELCOME
    )

    override suspend fun handleEvent(event: OnboardingContract.Event) {
        when(event) {
            is OnboardingContract.Event.NextStep -> nextStep()
            is OnboardingContract.Event.SetOnboardingStep -> setState { copy(onboardingStep = event.step) }
        }
    }

    private fun nextStep() {
        when(currentState.onboardingStep) {
            OnboardingContract.OnboardingStep.WELCOME -> setState { copy(onboardingStep = OnboardingContract.OnboardingStep.UNIQUE_QR_CODE) }
            OnboardingContract.OnboardingStep.UNIQUE_QR_CODE -> setState { copy(onboardingStep = OnboardingContract.OnboardingStep.CREATE_SHARE_EVENT) }
            OnboardingContract.OnboardingStep.CREATE_SHARE_EVENT -> setState { copy(onboardingStep = OnboardingContract.OnboardingStep.CONNECT_AND_COLLECT) }
            OnboardingContract.OnboardingStep.CONNECT_AND_COLLECT -> setState { copy(onboardingStep = OnboardingContract.OnboardingStep.READY_TO_SOCIALIZE) }
            OnboardingContract.OnboardingStep.READY_TO_SOCIALIZE -> {}
        }
    }
}
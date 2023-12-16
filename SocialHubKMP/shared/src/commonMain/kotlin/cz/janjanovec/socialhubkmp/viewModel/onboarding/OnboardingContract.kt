package cz.janjanovec.socialhubkmp.viewModel.onboarding

import cz.janjanovec.socialhubkmp.model.Account
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface OnboardingContract {
    data class State(
        var onboardingStep: OnboardingStep
    ): UiState

    sealed interface Event: UiEvent {
        object NextStep: Event
        data class SetOnboardingStep(val step: OnboardingStep): Event
    }

    enum class OnboardingStep(
        val stepNumber: Int,
        val title: String,
        val description: String,
        val image: String
    ) {
        WELCOME(
            1,
            "Welcome to Social Hub",
            "Prepare to simplify your social experience with Social Hub. Your personalized QR code is the key to seamless event creation, connection building, and more. Let's dive in!",
            "Image: An inviting graphic featuring diverse individuals engaged in various social activities, including networking, event creation, and connecting."
        ),
        UNIQUE_QR_CODE(
            2,
            "Your Unique QR Code",
            "This is your unique QR code – the gateway to all things social on Social Hub. Share, scan, and connect effortlessly. Your personalized code ensures a seamless and secure experience.",
            "Image: A clear and prominent image of the user's personalized QR code displayed on a smartphone screen, emphasizing its uniqueness."
        ),
        CREATE_SHARE_EVENT(
            3,
            "Create and Share Events",
            "Use your QR code to create events and share them with ease. Let others scan your code to join your gatherings. It's time to turn every event into a memorable experience!",
            "Image: A dynamic visual of someone creating an event on the app, with vibrant colors and event-related icons (such as a calendar, location pin, and people)."
        ),
        CONNECT_AND_COLLECT(
            4,
            "Connect and Collect with QR",
            "Scan the QR codes of others to connect instantly. Your connections will be neatly organized within the app. It's the easiest way to build and manage your network.",
            "Image: Illustration of two smartphones scanning QR codes, showcasing the seamless process of connecting with others on the app."
        ),
        READY_TO_SOCIALIZE(
            5,
            "Ready to Socialize!",
            "You're all set! Your QR code is your passport to a vibrant social experience. Start creating, connecting, and collecting today. Let's make every interaction count in your Social Hub!",
            "Image: A lively image of people enjoying a social gathering, possibly holding smartphones with the Social Hub app open, indicating a successful and enjoyable social experience."
        );
        companion object {
            val items: List<OnboardingStep>
                get() = values().toList()
        }
    }

}
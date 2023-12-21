import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var globalSettings: GlobalSettingsVM = .init()
    var body: some View {
        Group {
            switch globalSettings.state.appState {
            case is GlobalSettingsContractApplicationStateLoading:
                ProgressView()
            case is GlobalSettingsContractApplicationStateOnboarding:
                OnboardingView()
            case is GlobalSettingsContractApplicationStateLoggedIn:
                MainScreen()
            case is GlobalSettingsContractApplicationStateLoggedOut:
                InitialAuthView()
            default:
                EmptyView()
            }
        }
        .environmentObject(globalSettings)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

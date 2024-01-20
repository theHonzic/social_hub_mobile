import JJNavigation
import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var globalSettings: GlobalSettingsVM = .init()
    @StateObject private var globalSheetCoordinator: SheetCoordinator<GlobalSheetCoordinator> = .init()
    
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
        .sheetCoordinating(coordinator: globalSheetCoordinator)
        .onChange(of: globalSettings.currentState) { _, newValue in
            if let state = newValue.appState as? GlobalSettingsContractApplicationStateLoggedIn {
                if !state.additionalAccountData.isEmpty {
                    globalSheetCoordinator.presentSheet(.additionalAccountInfo(data: state.additionalAccountData))
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

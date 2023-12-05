import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var globalSettings: GlobalSettingsVM = .init()
    var body: some View {
        Group {
           /*
            if globalSettings.state.loadingProgress <= 0.99 {
                 AppLoadingView()
             } else {
                 if globalSettings.userLoggedIn {
                     MainScreen()
                 } else {
                     InitialAuthView()
                 }
             }
            */
            InitialAuthView()
        }
        .environmentObject(globalSettings)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

import shared
import SwiftUI

@main
struct SocialHubApp: App {
    init() {
        KoinKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

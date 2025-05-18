import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    let deps = Deps()

    var body: some Scene {
        WindowGroup {
            ComposeViewControllerRepresentable(deps: deps)
                .ignoresSafeArea(.all)
        }
    }
}

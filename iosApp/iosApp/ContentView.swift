import UIKit
import SwiftUI
import ComposeApp

struct ContentView: View {
    let deps: Deps
    init(deps: Deps) {
        self.deps = deps
    }
    
    var body: some View {
        ComposeViewControllerRepresentable(deps: deps)
            .ignoresSafeArea(.all)
    }
}

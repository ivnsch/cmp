import UIKit
import SwiftUI
import ComposeApp

struct ContentView: View {
    var body: some View {
        ComposeViewControllerRepresentable()
            .ignoresSafeArea(.all)
    }
}

import UIKit
import SwiftUI
import ComposeApp

struct ComposeViewControllerRepresentable: UIViewControllerRepresentable {
    let deps: Deps
    init(deps: Deps) {
        self.deps = deps
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return ComposeWithUIViewControllerKt.create(createUIViewController: { () -> UIViewController in
            let sceneView = MySceneView()

            Task {
                for try await radians in deps.webSockets.radiansFlow() {
                    print("Received in Swift: \(radians)")
                    sceneView.rotateShip(by: radians.doubleValue)
                }
            }
            
            let swiftUIView = VStack {
                Text("SwiftUI in Compose Multiplatform")
                Button("Press me", action: {
                    print("!!!")
                    let greeting = Greeting().greet()
                    print(greeting)
                    Greeting().greetPrint(parameter: "myname")
                    // this is a bit awkard, since as an object it shouldn't have to be instantiated?
                    GreetingObj().greetPrintFunctionInObj()
                    GreetingKt.greetPrintFunction()
                })
                sceneView
            }
            return UIHostingController(rootView: swiftUIView)
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

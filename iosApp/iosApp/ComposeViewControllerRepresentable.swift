import UIKit
import SwiftUI
import ComposeApp

struct ComposeViewControllerRepresentable: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainWithEmbeddedViewControllerKt.ComposeEntryPointWithUIViewController(createUIViewController: { () -> UIViewController in
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
                MySceneView()
            }
            return UIHostingController(rootView: swiftUIView)
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

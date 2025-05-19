import UIKit
import SwiftUI
import ComposeApp

struct ComposeViewControllerRepresentable: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        return ComposeWithUIViewControllerKt.create(createUIViewController: { () -> UIViewController in
            SceneViewController()
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

class SceneViewController: UIViewController {
    let sceneView = MySceneView()

    override func viewDidLoad() {
        super.viewDidLoad()
        let swiftUIView = createSwiftUIView()
        addAsHostingController(view: swiftUIView)
    }
    
    private func createSwiftUIView() -> some View {
        VStack {
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
    }
    
    private func addAsHostingController<V: View>(view: V) {
        let hostingController = UIHostingController(rootView: view)
        hostingController.view.frame = self.view.bounds
        hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        hostingController.didMove(toParent: self)
        addChild(hostingController)
        self.view.addSubview(hostingController.view)
    }
    
    @objc func updateRadians(_ radians: Double) {
        print("Received in Swift: \(radians)")
        sceneView.rotateShip(by: radians)
    }
}

import UIKit
import SwiftUI
import ComposeApp

struct ComposeViewControllerRepresentable: UIViewControllerRepresentable {
    let deps: Deps
    init(deps: Deps) {
        self.deps = deps
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return ComposeWithUIViewControllerKt.create(deps: deps, createUIViewController: { () -> UIViewController in
            SceneViewController(deps: deps)
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

class SceneViewController: UIViewController {
    let deps: Deps
    
    let sceneView = MySceneView()

    init(deps: Deps) {
        self.deps = deps
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
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

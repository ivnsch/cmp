import SwiftUI
import SceneKit

struct MySceneView: View {
    @State private var scene: SCNScene = {
        let scene = SCNScene(named: "art.scnassets/ship.scn")!

        // Camera
        let cameraNode = SCNNode()
        cameraNode.camera = SCNCamera()
        cameraNode.position = SCNVector3(x: 0, y: 0, z: 15)
        scene.rootNode.addChildNode(cameraNode)

        // Omni Light
        let lightNode = SCNNode()
        lightNode.light = SCNLight()
        lightNode.light!.type = .omni
        lightNode.position = SCNVector3(x: 0, y: 10, z: 10)
        scene.rootNode.addChildNode(lightNode)

        // Ambient Light
        let ambientLightNode = SCNNode()
        ambientLightNode.light = SCNLight()
        ambientLightNode.light!.type = .ambient
        ambientLightNode.light!.color = UIColor.darkGray
        scene.rootNode.addChildNode(ambientLightNode)
        
        return scene
    }()
    
    func ship(scene: SCNScene) -> SCNNode? {
        return scene.rootNode.childNode(withName: "ship", recursively: true)
    }

    var body: some View {
        SceneView(
            scene: scene,
            options: [
                .allowsCameraControl,
                .autoenablesDefaultLighting,
                .temporalAntialiasingEnabled
            ]
        )
        .background(Color.black)
        .edgesIgnoringSafeArea(.all)
    }
    
    func rotateShip(by: Double) {
        let action = SCNAction.rotateBy(x: 0, y: 2, z: 0, duration: 1)
        let ship = ship(scene: scene)
        ship?.runAction(action)
    }
}

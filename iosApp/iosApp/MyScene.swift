import SwiftUI
import SceneKit

struct MySceneView: View {
    var scene: SCNScene {
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

        // Ship Animation
        if let ship = scene.rootNode.childNode(withName: "ship", recursively: true) {
            let rotateAction = SCNAction.repeatForever(SCNAction.rotateBy(x: 0, y: 2, z: 0, duration: 1))
            ship.runAction(rotateAction)
        }

        return scene
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
}

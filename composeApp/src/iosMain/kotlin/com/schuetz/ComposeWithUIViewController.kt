package com.schuetz

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController
import platform.darwin.sel_registerName

@Suppress("unused")
fun create(
    deps: Deps,
    createUIViewController: () -> UIViewController
): UIViewController {
    val viewModel = MainViewModel(deps.webSockets)
    return ComposeUIViewController {
        MainScreen(viewModel, embedded = { radians ->
            IOSEmbedded(createUIViewController, radians)
        })
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
fun IOSEmbedded(
    createUIViewController: () -> UIViewController,
    radians: Double
) {
    UIKitViewController(
        factory = createUIViewController,
        modifier = Modifier.size(300.dp).border(2.dp, Color.Blue),
        update = { viewController ->
            val selector = sel_registerName("updateRadians:")
            if (viewController.respondsToSelector(selector)) {
                viewController.performSelector(
                    selector,
                    withObject = radians
                )
            }
        },
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}

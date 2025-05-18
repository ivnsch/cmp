package com.schuetz

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("unused")
fun create(
    createUIViewController: (Double) -> UIViewController
): UIViewController {
    val rotateBy = 2.0

    return ComposeUIViewController {
        MainContent(embedded = {
            UIKitViewController(
                factory = {
                    createUIViewController(rotateBy)
                },
                modifier = Modifier.size(300.dp).border(2.dp, Color.Blue),
                properties = UIKitInteropProperties(
                    isInteractive = true,
                    isNativeAccessibilityEnabled = true
                )
            )
        })
    }
}

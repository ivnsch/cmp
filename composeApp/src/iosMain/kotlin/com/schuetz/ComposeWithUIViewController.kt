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
    createUIViewController: () -> UIViewController
): UIViewController {
    return ComposeUIViewController {
        MainContent(embedded = {
            UIKitViewController(
                factory = createUIViewController,
                modifier = Modifier.size(300.dp).border(2.dp, Color.Blue),
                properties = UIKitInteropProperties(
                    isInteractive = true,
                    isNativeAccessibilityEnabled = true
                )
            )
        })
    }
}

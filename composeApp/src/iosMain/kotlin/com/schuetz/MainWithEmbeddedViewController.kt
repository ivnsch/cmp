package com.schuetz

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
fun ComposeEntryPointWithUIViewController(
    createUIViewController: (Double) -> UIViewController
): UIViewController {
    val rotateBy = 2.0
    val deps = Deps()

    return ComposeUIViewController {
        Column(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("How to use SwiftUI inside Compose Multiplatform")
            UIKitViewController(
                factory = {
                    createUIViewController(rotateBy)
                },
                modifier = Modifier.size(300.dp).border(2.dp, Color.Blue),
            )
        }
    }
}


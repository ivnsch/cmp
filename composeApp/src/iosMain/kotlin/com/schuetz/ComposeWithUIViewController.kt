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
import com.schuetz.di.initKoin
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.compose.viewmodel.koinViewModel
import platform.UIKit.UIViewController
import platform.darwin.sel_registerName

@Suppress("unused")
fun create(
    createUIViewController: () -> UIViewController
): UIViewController {
    return ComposeUIViewController(configure = {
        initKoin()
    }) {
        val viewModel = koinViewModel<MainViewModel>()
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

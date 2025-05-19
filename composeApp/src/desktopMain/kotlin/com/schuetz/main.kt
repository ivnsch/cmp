package com.schuetz

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.schuetz.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Agents",
    ) {
        App()
    }
}
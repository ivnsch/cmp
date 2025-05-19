package com.schuetz

import androidx.lifecycle.ViewModel

class MainViewModel(
    webSockets: WebSockets
) : ViewModel() {
    val radians = webSockets.radiansFlow()
}

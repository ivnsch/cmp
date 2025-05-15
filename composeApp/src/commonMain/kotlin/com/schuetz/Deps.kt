package com.schuetz

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.websocket.WebSockets

class Deps {
    val client by lazy { HttpClientFactory.create(engine) }
    val socketsClient by lazy {
        HttpClient {
            install(WebSockets)
        }
    }
}

expect val engine: HttpClientEngine

expect val host: String

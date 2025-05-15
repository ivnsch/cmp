package com.schuetz

import io.ktor.client.engine.HttpClientEngine

class Deps {
    val client by lazy { HttpClientFactory.create(engine) }
}

expect val engine: HttpClientEngine

expect val host: String

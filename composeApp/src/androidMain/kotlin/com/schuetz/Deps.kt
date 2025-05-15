package com.schuetz

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual val engine: HttpClientEngine = OkHttp.create()

actual val host: String = "10.0.2.2"

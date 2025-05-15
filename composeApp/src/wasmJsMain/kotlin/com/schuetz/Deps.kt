package com.schuetz

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.JsClient

actual val engine: HttpClientEngine = JsClient().create()

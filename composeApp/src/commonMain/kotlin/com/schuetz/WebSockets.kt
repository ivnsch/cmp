package com.schuetz

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WebSockets(private val client: HttpClient) {
    fun radiansFlow(): Flow<Double> = flow {
        client.webSocket(
            method = HttpMethod.Get,
            host = host,
            port = 8080,
            path = "/radians"
        ) {
            println("Connected to websocket")
            send("start")
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val message = frame.readText()
                    println("Received number: $message")
                    message.toDoubleOrNull()?.let { emit(it) }
                }
            }
        }
    }
}

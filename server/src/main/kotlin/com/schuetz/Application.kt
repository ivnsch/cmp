package com.schuetz

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlin.time.Duration.Companion.seconds
import io.ktor.websocket.send
import kotlinx.coroutines.delay
import kotlin.random.Random

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowHost("localhost:8081")
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Options)
    }

    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        webSocket("/radians") {

            for (frame in incoming) {
                frame as? Frame.Text ?: continue

                when (val receivedText = frame.readText()) {
                    "bye" -> close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                    "start" -> while (true) {
                        send("${Random.nextDouble(0.0, 1.0)}")
                        delay(2000)
                    }

                    else -> send("unexpected payload: $receivedText!")
                }
            }
        }
    }
}
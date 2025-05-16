package com.schuetz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import agents.composeapp.generated.resources.Res
import agents.composeapp.generated.resources.compose_multiplatform
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send

@Composable
@Preview
fun App(deps: Deps) {
    var httpResponseResult by remember { mutableStateOf<Result<HttpResponse>?>(null) }
    LaunchedEffect(deps.client) {
        val serverUrl = "http://$host:8080"
        httpResponseResult = deps.client.safeGet(serverUrl)
        println("result: $httpResponseResult")

        deps.socketsClient.webSocket(
            method = HttpMethod.Get,
            host = host,
            port = 8080,
            path = "/echo"
        ) {
            val othersMessage = incoming.receive() as? Frame.Text
            println(othersMessage?.readText())
            send("somename")
            val reply = incoming.receive() as? Frame.Text
            println(reply?.readText())

            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val number = frame.readText()
                    println("Received number: $number")
                }
            }
        }
    }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(httpResponseResult.toString())
            Button(onClick = {
                showContent = !showContent


            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
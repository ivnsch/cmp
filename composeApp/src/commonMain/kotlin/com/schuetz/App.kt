package com.schuetz

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.catch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(deps: Deps) {
    MainContent(deps, embedded = { radians ->
        CommonEmbedded(radians)
    })
}

@Composable
fun CommonEmbedded(radians: Double) {
    Box(
        modifier = Modifier.size(300.dp).border(2.dp, Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text("$radians")
    }
}

@Composable
fun MainContent(deps: Deps, embedded: @Composable (Double) -> Unit) {
    val radians = deps.webSockets.radiansFlow()
        .catch { e ->
            println("Error in radiansFlow: ${e.message}")
            e.printStackTrace()
        }
        .collectAsState(initial = 0.0)

    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            embedded(radians.value)
        }
    }
}

package com.schuetz

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

suspend fun HttpClient.safeGet(url: String): Result<HttpResponse> {
    return runCatching {
        this.get(url)
    }
}

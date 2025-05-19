package com.schuetz.di

import com.schuetz.HttpClientFactory
import com.schuetz.WebSockets
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.schuetz.MainViewModel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    single {
        HttpClient {
            install(io.ktor.client.plugins.websocket.WebSockets)
        }
    }
    single { WebSockets(get()) }

    viewModelOf(::MainViewModel)
}

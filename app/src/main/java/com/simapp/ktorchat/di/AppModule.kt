package com.simapp.ktorchat.di

import com.simapp.ktorchat.data.remote.ChatSocketService
import com.simapp.ktorchat.data.remote.ChatSocketServiceImpl
import com.simapp.ktorchat.data.remote.MessageService
import com.simapp.ktorchat.data.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
           install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = true
                        coerceInputValues = true
                        encodeDefaults = true
                        allowSpecialFloatingPointValues = true
                        allowStructuredMapKeys = true
                        useArrayPolymorphism = true
                        useAlternativeNames =
                            true // Disabling this flag when one does not use JsonNames a
                    },

                    contentType = ContentType.Any // body response type
                )

            }
        }
    }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): MessageService {
        return MessageServiceImpl(client)
    }


    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }
}
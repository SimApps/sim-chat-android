package com.simapp.ktorchat.data.remote

import com.simapp.ktorchat.domain.model.Message

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object {
       // const val BASE_URL = "http://10.0.2.2:8080"
        const val BASE_URL = "http://192.168.1.14:8080"
    }

    sealed class EndPoints(val url:String) {
        object GetAllMessages: EndPoints("$BASE_URL/messages")
    }
}
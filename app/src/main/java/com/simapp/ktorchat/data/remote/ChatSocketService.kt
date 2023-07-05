package com.simapp.ktorchat.data.remote

import com.simapp.ktorchat.domain.model.Message
import com.simapp.ktorchat.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {
     suspend fun initSession(
         username: String
     ): Resource<Unit>

     suspend fun sendMessage(message: String)

      fun observeMessages(): Flow<Message>

      suspend fun closeSession()


    companion object {
       // const val BASE_URL = "ws://10.0.2.2:8080"
        const val BASE_URL = "ws://192.168.1.14:8080"
    }

    sealed class EndPoints(val url:String) {
        object ChatSocket: EndPoints("$BASE_URL/chat-socket")
    }
}
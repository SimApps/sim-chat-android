package com.simapp.ktorchat.presentation.chat

import com.simapp.ktorchat.domain.model.Message

data class ChatState (
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
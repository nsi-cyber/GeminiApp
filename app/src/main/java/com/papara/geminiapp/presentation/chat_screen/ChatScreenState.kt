package com.papara.geminiapp.presentation.chat_screen

import com.papara.geminiapp.data.local.entity.ChatMessage


data class ChatScreenState(
    val chatList: MutableList<ChatMessage> = mutableListOf(),
    val prompt: String = "",
    val isLoading:Boolean=false,
    val isTyping:Boolean=false
)


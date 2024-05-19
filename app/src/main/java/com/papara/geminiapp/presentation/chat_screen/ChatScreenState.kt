package com.papara.geminiapp.presentation.chat_screen

data class Chat(
    val prompt: String,
    val isFromUser: Boolean
)
data class ChatScreenState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = ""
)


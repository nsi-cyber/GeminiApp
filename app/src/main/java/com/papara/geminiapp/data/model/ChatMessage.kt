package com.papara.geminiapp.data.model

data class ChatMessage(
    val sender: String,
    val content: String,
    val timestamp: Long
)

package com.papara.geminiapp.presentation.chat_screen

sealed class ChatScreenEvent() {
    data class UpdatePrompt(val newPrompt: String) : ChatScreenEvent()
    data class SendPrompt(val prompt: String) : ChatScreenEvent()
}

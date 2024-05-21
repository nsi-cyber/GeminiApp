package com.papara.geminiapp.presentation.chat_screen

sealed class ChatScreenEvent() {
    data class UpdatePrompt(val newPrompt: String) : ChatScreenEvent()
    data class SendPrompt(val prompt: String) : ChatScreenEvent()
    data object TypingFinished : ChatScreenEvent()
    data object TypingStarted : ChatScreenEvent()
    data class LoadConversation(val conversationId: Long) : ChatScreenEvent()
}

package com.papara.geminiapp.presentation.chat_screen

import com.papara.geminiapp.data.local.entity.Conversation

sealed class ChatScreenEvent() {
    data class UpdatePrompt(val newPrompt: String) : ChatScreenEvent()
    data class SendPrompt(val prompt: String) : ChatScreenEvent()
    data class LoadConversation(val conversationId: Long) : ChatScreenEvent()
}

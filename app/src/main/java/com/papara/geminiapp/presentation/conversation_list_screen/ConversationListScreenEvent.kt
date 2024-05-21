package com.papara.geminiapp.presentation.conversation_list_screen

import com.papara.geminiapp.data.local.entity.Conversation


sealed class ConversationListScreenEvent() {
    data class DeleteConversation(val conversation: Conversation) : ConversationListScreenEvent()
    data object OnLoading : ConversationListScreenEvent()
}

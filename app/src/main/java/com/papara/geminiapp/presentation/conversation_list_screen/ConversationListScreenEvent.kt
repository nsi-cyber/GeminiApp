package com.papara.geminiapp.presentation.conversation_list_screen


sealed class ConversationListScreenEvent() {
    data class OpenConversation(val conversationId: String) : ConversationListScreenEvent()
    data class DeleteConversation(val conversationId: String) : ConversationListScreenEvent()
}

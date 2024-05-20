package com.papara.geminiapp.domain.repository

import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation


interface ChatRepository  {
    suspend fun getMessagesByConversationId(conversationId:Long?):  List<ChatMessage>

    suspend fun insertChatMessage( chatMessage: ChatMessage)
    suspend fun createConversation( conversation: Conversation):Long
    suspend fun getAllConversations():List<Conversation>

    suspend fun clearConversation(conversationId: String)
}
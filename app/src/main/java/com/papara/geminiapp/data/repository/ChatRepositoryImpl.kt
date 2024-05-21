package com.papara.geminiapp.data.repository

import com.papara.geminiapp.data.local.dao.ConversationDao
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.domain.repository.ChatRepository
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(
    private val conversationDao: ConversationDao,
) : ChatRepository {
    override suspend fun getMessagesByConversationId(conversationId: Long?): List<ChatMessage> {
        return conversationDao.getMessagesByConversationId(conversationId)
    }

    override suspend fun insertChatMessage(chatMessage: ChatMessage) {
        conversationDao.insertChatMessage(chatMessage)
    }

    override suspend fun createConversation(conversation: Conversation): Long {
        return conversationDao.createConversation(conversation)
    }

    override suspend fun deleteConversation(conversation: Conversation)  {
         conversationDao.deleteConversation(conversation)
    }

    override suspend fun getAllConversations(): List<Conversation> {
        return conversationDao.getAllConversations()
    }




}
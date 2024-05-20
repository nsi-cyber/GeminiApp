package com.papara.geminiapp.data.repository

import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.dao.ConversationDao
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.data.remote.ApiService
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import com.papara.geminiapp.domain.repository.ApiRepository
import com.papara.geminiapp.domain.repository.ChatRepository
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(
    private val chatMessageDao: ChatMessageDao,
    private val conversationDao: ConversationDao,
) : ChatRepository {
    override suspend fun getMessagesByConversationId(conversationId: Long?): List<ChatMessage> {
        return conversationDao.getMessagesByConversationId()
    }



    override suspend fun insertChatMessage(chatMessage: ChatMessage) {
        chatMessageDao.insertChatMessage(chatMessage)
    }

    override suspend fun createConversation(conversation: Conversation): Long {
        return conversationDao.createConversation(conversation)
    }

    override suspend fun getAllConversations(): List<Conversation> {
        return conversationDao.getAllConversations()
    }

    override suspend fun clearConversation(conversationId: String) {
        TODO("Not yet implemented")
    }


}
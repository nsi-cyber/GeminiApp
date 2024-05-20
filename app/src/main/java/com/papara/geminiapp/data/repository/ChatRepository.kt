package com.papara.geminiapp.data.repository

import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.entity.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ChatRepository @Inject constructor(
    private val chatMessageDao: ChatMessageDao
) {
    fun getAllChatMessages(): List<ChatMessage> {
        return chatMessageDao.getAllChatMessages()
    }

    suspend fun insertChatMessage(chatMessage: ChatMessage) {
        chatMessageDao.insertChatMessage(chatMessage)
    }


        suspend fun clearChatMessages() {
            chatMessageDao.clearChatMessages()
        }
    }

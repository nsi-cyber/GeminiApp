package com.papara.geminiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation

@Dao
interface ConversationDao {

    @Query("SELECT * FROM chat_messages WHERE conversationId = :conversationId")
    suspend fun getMessagesByConversationId(conversationId: Int): List<ChatMessage>

    @Query("SELECT * FROM chat_messages WHERE conversationId = :conversationId")
    suspend fun getMessagesByConversationId(conversationId: Int): List<ChatMessage>

    @Query("SELECT * FROM conversations")
    suspend fun getAllConversations(): List<Conversation>

}
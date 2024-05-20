package com.papara.geminiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation

@Dao
interface ConversationDao {

    //@Query("SELECT * FROM chat_messages WHERE conversationId = :conversationId")
    @Query("SELECT * FROM chat_messages ")
    suspend fun getMessagesByConversationId(): List<ChatMessage>


    @Query("SELECT * FROM conversations")
    suspend fun getAllConversations(): List<Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createConversation(conversation: Conversation): Long

}
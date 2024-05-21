package com.papara.geminiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation

@Dao
interface ConversationDao {

    //@Query("SELECT * FROM chat_messages WHERE conversationId = :conversationId")
    @Query("SELECT * FROM chat_messages WHERE conversationId=:conversationId ")
    suspend fun getMessagesByConversationId(conversationId:Long?): List<ChatMessage>


    @Query("SELECT * FROM conversations")
    suspend fun getAllConversations(): List<Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createConversation(conversation: Conversation): Long

    @Delete

    suspend fun deleteConversation(conversation: Conversation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessage(chatMessage: ChatMessage)



}
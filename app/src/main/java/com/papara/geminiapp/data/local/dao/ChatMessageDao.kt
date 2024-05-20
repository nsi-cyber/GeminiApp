package com.papara.geminiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papara.geminiapp.data.local.entity.ChatMessage

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages WHERE id = :conversationId ORDER BY timestamp ASC")
    fun getAllChatMessages(conversationId: String): List<ChatMessage>


    @Query("INSERT INTO chat_messages (id, message, isFromUser, timestamp) VALUES (:conversationId, :chatMessage.message, :chatMessage.isFromUser, :chatMessage.timestamp)")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessage(conversationId: String, chatMessage: ChatMessage)


    @Query("DELETE FROM chat_messages")
    suspend fun clearChatMessages()
}
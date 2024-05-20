package com.papara.geminiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val conversationId: Long,
    val message: String,
    val isFromUser: Boolean,
    val timestamp: Long=System.currentTimeMillis()
)
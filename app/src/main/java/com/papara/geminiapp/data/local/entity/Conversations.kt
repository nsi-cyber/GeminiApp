package com.papara.geminiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "conversations")
    data class Conversation(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val description: String,
        val messages: List<ChatMessage>
    )


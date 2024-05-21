package com.papara.geminiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.papara.geminiapp.data.local.database.ChatMessageListConverter
import com.papara.geminiapp.data.models.ConversationModel


@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
)



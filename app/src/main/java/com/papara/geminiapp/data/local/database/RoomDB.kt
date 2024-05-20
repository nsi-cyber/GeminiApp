package com.papara.geminiapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.entity.ChatMessage

@Database(entities = [ChatMessage::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}
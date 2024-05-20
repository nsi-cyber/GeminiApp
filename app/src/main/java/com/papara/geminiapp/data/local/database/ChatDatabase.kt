package com.papara.geminiapp.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.dao.ConversationDao
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation

@Database(entities = [ChatMessage::class,Conversation::class], version = 2)
@TypeConverters(ChatMessageListConverter::class)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun conversationDao(): ConversationDao

}


class ChatMessageListConverter {

    @TypeConverter
    fun fromChatMessageList(messages: List<ChatMessage>): String {
        val gson = Gson()
        return gson.toJson(messages)
    }

    @TypeConverter
    fun toChatMessageList(messagesString: String): List<ChatMessage> {
        val gson = Gson()
        val type = object : TypeToken<List<ChatMessage>>() {}.type
        return gson.fromJson(messagesString, type)
    }
}

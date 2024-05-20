package com.papara.geminiapp.di

import android.content.Context
import androidx.room.Room
import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.database.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return Room.databaseBuilder(
            appContext,
            ChatDatabase::class.java,
            "chat_database"
        ).build()
    }

    @Provides
    fun provideChatMessageDao(chatDatabase: ChatDatabase): ChatMessageDao {
        return chatDatabase.chatMessageDao()
    }
}
package com.papara.geminiapp.di

import android.content.Context
import androidx.room.Room
import com.papara.geminiapp.data.local.dao.ChatMessageDao
import com.papara.geminiapp.data.local.dao.ConversationDao
import com.papara.geminiapp.data.local.database.ChatDatabase
import com.papara.geminiapp.data.remote.ApiService
import com.papara.geminiapp.data.repository.ApiRepositoryImpl
import com.papara.geminiapp.data.repository.ChatRepositoryImpl
import com.papara.geminiapp.domain.repository.ApiRepository
import com.papara.geminiapp.domain.repository.ChatRepository
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

    @Provides
    fun provideConversationDao(chatDatabase: ChatDatabase): ConversationDao {
        return chatDatabase.conversationDao()
    }


    @Provides
    @Singleton
    fun provideChatRepository(
        chatDao: ChatMessageDao,
        conversationDao: ConversationDao
    ): ChatRepository {
        return ChatRepositoryImpl(chatMessageDao = chatDao, conversationDao = conversationDao)
    }

}
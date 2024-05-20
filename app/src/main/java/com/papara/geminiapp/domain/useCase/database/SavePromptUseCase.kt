package com.papara.geminiapp.domain.useCase.database

import android.util.Log
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class SavePromptUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    operator fun invoke(chatMessage: ChatMessage): Flow<Unit> =
        flow {
            try {
                emit(repo.insertChatMessage(chatMessage))
            }
            catch (e: Exception){
                Log.e("GetMessagesUseCase Error: ",e.toString())
            }
        }
}
package com.papara.geminiapp.domain.useCase.chat

import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import com.papara.geminiapp.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SendMessageUseCase @Inject constructor(
    private val repo: ApiRepository
) {
    operator fun invoke(apiKey:String?, body: MessageRequestBody): Flow<Resource<MessageResponse?>> =
        flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repo.sendMessage(apiKey, body)))
        }
            catch (e:Exception){
                emit(Resource.Error(message = e.message.toString()))

            }
    }
}
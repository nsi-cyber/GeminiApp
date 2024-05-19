package com.papara.geminiapp.domain.useCase.chat

import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import com.papara.geminiapp.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


class SendMessageUseCase @Inject constructor(
    private val repo: ApiRepository
) {
    operator fun invoke(apiKey:String?, body: MessageRequestBody): Flow<Resource<MessageResponse?>> =
        flow {
        try {
            println("hereee")
            emit(Resource.Loading())
            emit(Resource.Success(repo.sendMessage(apiKey, body)))
        } catch(e: HttpException) {
            println("hereee")

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            println("hereee")

            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
            catch (_:Exception){
                println("hereee")

            }
    }
}
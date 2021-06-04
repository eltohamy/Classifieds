package com.dubizzle.classifieds.domain.usecases

import com.dubizzle.classifieds.data.network.ResultData
import com.dubizzle.classifieds.data.repository.DataRepository
import com.dubizzle.classifieds.domain.models.getclassifieds.ClassifiedsResponse
import com.dubizzle.classifieds.domain.models.getclassifieds.Result
import javax.inject.Inject

class GetClassifiedsUseCase @Inject
 constructor(
     private val dataRepository: DataRepository) {
     suspend fun getClassifiedsList(): ResultData<ClassifiedsResponse> {
         return try {
             val getClassifiedsListResponse = dataRepository.getClassifiedsList()
             when (getClassifiedsListResponse.results == null) {
                 true -> {
                     ResultData.Failed()
                 }
                 else -> {
                     ResultData.Success(getClassifiedsListResponse)
                 }
             }
         } catch (ex: Exception) {
             ex.printStackTrace()
             ResultData.Exception(ex)
         }
     }
 }
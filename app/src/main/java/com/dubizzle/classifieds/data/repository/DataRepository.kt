package com.dubizzle.classifieds.data.repository

import com.dubizzle.classifieds.data.network.ApiService
import com.dubizzle.classifieds.domain.models.getclassifieds.ClassifiedsResponse
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getClassifiedsList(): ClassifiedsResponse {
        return apiService.getClassifiedsList()
    }
}
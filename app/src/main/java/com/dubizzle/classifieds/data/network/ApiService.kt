package com.dubizzle.classifieds.data.network

import com.dubizzle.classifieds.domain.models.getclassifieds.ClassifiedsResponse
import retrofit2.http.GET

interface ApiService {
    @GET(NetworkingConstants.GET_CLASSIFIEDS_LIST)
    suspend fun getClassifiedsList(): ClassifiedsResponse
}
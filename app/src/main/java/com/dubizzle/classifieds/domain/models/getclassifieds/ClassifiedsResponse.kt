package com.dubizzle.classifieds.domain.models.getclassifieds

data class ClassifiedsResponse(
    val pagination: Pagination?,
    val results: List<Result>?
)
package org.wahid.borutoappversion1.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse (
    val success:Boolean,
    val message:String? = null,
    val previousPage:Int? = null,
    val nextPage:Int? = null,
    val heroes:List<Hero> = emptyList()
)
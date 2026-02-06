package com.example.mobile.data.api

import com.example.mobile.data.dto.DropdownItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LookupsApi {

    @GET("api/categorias-productos")
    suspend fun getCategories(): List<DropdownItemDto>

    @GET("api/categorias-productos")
    suspend fun getBusinesses(
        @Query("categoryId") categoryId: Int
    ): List<DropdownItemDto>
}

package com.example.mobile.data.api

import com.example.mobile.data.dto.ApiResponse
import com.example.mobile.data.dto.CategoriaProductoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoriasProductosApi {

    // Lista: /api/categorias-productos?estado=1
    @GET("api/categorias-productos")
    suspend fun getCategorias(@Query("estado") estado: Int = 1): List<CategoriaProductoDto>

    // Detalle: /api/categorias-productos/{id}
    @GET("api/categorias-productos/{id}")
    suspend fun getCategoriaById(@Path("id") id: Int): ApiResponse<CategoriaProductoDto>
}

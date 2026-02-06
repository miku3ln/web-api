package com.example.mobile.core.network

import com.example.mobile.core.config.ApiConfig
import com.example.mobile.data.api.CategoriasProductosApi
import com.example.mobile.data.api.LookupsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    @Volatile private var retrofit: Retrofit? = null

    fun categoriasApi(): CategoriasProductosApi {
        val r = retrofit ?: synchronized(this) {
            retrofit ?: buildRetrofit().also { retrofit = it }
        }
        return r.create(CategoriasProductosApi::class.java)
    }
    fun categoriaApi(): CategoriasProductosApi {
        val r = retrofit ?: synchronized(this) {
            retrofit ?: buildRetrofit().also { retrofit = it }
        }
        return r.create(CategoriasProductosApi::class.java)
    }
    fun lookupsApi(): LookupsApi {
        val r = retrofit ?: synchronized(this) {
            retrofit ?: buildRetrofit().also { retrofit = it }
        }
        return r.create(LookupsApi::class.java)
    }

    /**
     * Si cambias ApiConfig.env / deviceHostIp / useHttps,
     * llama a reset() para reconstruir Retrofit.
     */
    fun reset() {
        retrofit = null
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

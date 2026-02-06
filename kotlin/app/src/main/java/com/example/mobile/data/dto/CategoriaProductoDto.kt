package com.example.mobile.data.dto

data class CategoriaProductoDto(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val estado: Int,
    val fechaCreacion: String?,
    val fechaActualiza: String?
)

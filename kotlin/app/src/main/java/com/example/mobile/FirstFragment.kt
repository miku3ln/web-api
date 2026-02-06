package com.example.mobile

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobile.core.network.ApiClient
import com.example.mobile.data.dto.DropdownItemDto
import com.example.mobile.ui.common.onItemSelected
import kotlinx.coroutines.launch

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var spCategory: Spinner
    private lateinit var spBusiness: Spinner
    private lateinit var tvInfo: TextView

    // Dropdown data
    private var categories: List<DropdownItemDto> = emptyList()
    private var businesses: List<DropdownItemDto> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spCategory = view.findViewById(R.id.spCategory)
        tvInfo = view.findViewById(R.id.textview_first)


        loadCategories()
    }

    private fun loadCategories() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                tvInfo.text = "Cargando categorías..."

                // 1) Traer lista real desde /api/categorias-productos?estado=1
                val categoriasApi = ApiClient.categoriasApi().getCategorias(estado = 1)

                // 2) Mapear a DropdownItemDto (id + label)
                categories = categoriasApi.map { DropdownItemDto(it.id, it.nombre) }

                spCategory.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    categories.map { it.label }
                )

                tvInfo.text = "Categorías cargadas. Selecciona una."

                spCategory.onItemSelected { pos ->
                    val categoryId = categories[pos].id
                    loadCategoryDetail(categoryId)     // ✅ aquí usas /{id}

                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                tvInfo.text = "Error cargando categorías: ${ex.message}"
            }
        }
    }

    // ✅ Detalle por ID: /api/categorias-productos/{id}
    private fun loadCategoryDetail(categoryId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.categoriasApi().getCategoriaById(categoryId)

                if (response.success && response.data != null) {
                    val c = response.data
                    tvInfo.text = "Categoría Seleccionada: ${c.nombre}\n${c.descripcion ?: ""}"
                } else {
                    tvInfo.text = response.message
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
                tvInfo.text = "Error detalle categoría: ${ex.message}"
            }
        }
    }

}

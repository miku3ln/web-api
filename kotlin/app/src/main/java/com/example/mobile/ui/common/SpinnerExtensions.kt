package com.example.mobile.ui.common

import android.widget.AdapterView
import android.widget.Spinner

fun Spinner.onItemSelected(action: (position: Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: android.view.View?,
            position: Int,
            id: Long
        ) {
            action(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }
}

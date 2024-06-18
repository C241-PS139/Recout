package com.bangkit.recout.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.recout.data.pref.ProductRepository
import com.bangkit.recout.data.response.ProductResponseItem

class MainViewModel : ViewModel() {
    private val repository = ProductRepository()

    val products: LiveData<List<ProductResponseItem>> get() = repository.products

    fun fetchProducts() {
        repository.fetchProducts()
    }
}
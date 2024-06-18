package com.bangkit.recout.data.pref

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.recout.data.api.ApiConfig
import com.bangkit.recout.data.api.createRequestBody
import com.bangkit.recout.data.response.ProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {
    private val _products = MutableLiveData<List<ProductResponseItem>>()
    val products: LiveData<List<ProductResponseItem>> get() = _products

    fun fetchProducts() {
        val requestBody = createRequestBody()

        ApiConfig.api.getProducts(requestBody).enqueue(object : Callback<List<ProductResponseItem>> {
            override fun onResponse(call: Call<List<ProductResponseItem>>, response: Response<List<ProductResponseItem>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    _products.value = productList
                    Log.d("API Response", "Products: $productList")
                } else {
                    Log.e("API Error", "Response Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
            }
        })
    }
}
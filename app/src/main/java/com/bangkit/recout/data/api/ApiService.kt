package com.bangkit.recout.data.api

import com.bangkit.recout.data.response.ProductResponseItem
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("recommend")
    fun getProducts(@Body requestBody: RequestBody): Call<List<ProductResponseItem>>
}

fun createRequestBody(): RequestBody {
    val json = """
        {
            "gender_product": "Men",
            "city": "Sulawesi Selatan"
        }
    """.trimIndent()

    return json.toRequestBody("application/json".toMediaTypeOrNull())
}
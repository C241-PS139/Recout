package com.bangkit.recout.data.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @field:SerializedName("ProductResponse")
    val productResponse: List<ProductResponseItem>
)

data class ProductResponseItem(

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("productDisplayName")
    val productDisplayName: String? = null,

    @field:SerializedName("product_id")
    val productId: Int? = null,

    @field:SerializedName("usage")
    val usage: String? = null,

    @field:SerializedName("gender_product")
    val genderProduct: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("temperature")
    val temperature: String? = null
)
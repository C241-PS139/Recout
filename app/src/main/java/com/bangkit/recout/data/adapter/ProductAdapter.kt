package com.bangkit.recout.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.recout.R
import com.bangkit.recout.data.response.ProductResponseItem
import com.bumptech.glide.Glide

class ProductAdapter(private val productList: List<ProductResponseItem>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.tvProductName)
        private val productCity: TextView = itemView.findViewById(R.id.tvProductCity)
        private val productUsage: TextView = itemView.findViewById(R.id.tvProductUsage)
        private val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        private val productTemp: TextView = itemView.findViewById(R.id.tvTemp)

        fun bind(product: ProductResponseItem) {
            productName.text = product.productDisplayName
            productCity.text = product.city
            productUsage.text = product.usage
            productTemp.text = product.temperature

            Log.d("ProductAdapter", "Image URL: ${product.link}")

            Glide.with(itemView.context)
                .load(product.link)
                .placeholder(R.drawable.baseline_error_24)
                .error(R.drawable.baseline_error_24)
                .into(productImage)
        }
    }
}
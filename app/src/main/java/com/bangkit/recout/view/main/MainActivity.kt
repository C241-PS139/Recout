package com.bangkit.recout.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.recout.data.adapter.ProductAdapter
import com.bangkit.recout.databinding.ActivityMainBinding
import com.bangkit.recout.view.login.LoginActivity
import com.bangkit.recout.view.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private val productViewModel: MainViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        progressBar = binding.progressBar

        val recyclerView: RecyclerView = binding.listItem
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.products.observe(this) { products ->
            if (products != null) {
                val adapter = ProductAdapter(products)
                recyclerView.adapter = adapter
                showLoading(false)
            }
        }
        productViewModel.fetchProducts()

        val user = auth.currentUser
        if (user == null) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        } else {
            val userName = user.displayName ?: user.email
            binding.welcomeText.text = "Welcome,\n$userName"
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
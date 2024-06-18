package com.bangkit.recout.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.recout.databinding.ActivityLoginBinding
import com.bangkit.recout.view.main.MainActivity
import com.bangkit.recout.view.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                showLoading(true)
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Email & Password Incorrect!", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerText.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        playAnimation()
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(300)
        val email = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val pass = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val btn = ObjectAnimator.ofFloat(binding.btnSignIn, View.ALPHA, 1f).setDuration(300)
        val reg = ObjectAnimator.ofFloat(binding.reg, View.ALPHA, 1f).setDuration(300)


        AnimatorSet().apply {
            playSequentially(title, email, pass, btn, reg)
            start()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
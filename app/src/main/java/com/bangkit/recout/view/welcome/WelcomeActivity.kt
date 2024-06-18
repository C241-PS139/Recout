package com.bangkit.recout.view.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.recout.databinding.ActivityWelcomeBinding
import com.bangkit.recout.view.login.LoginActivity
import com.bangkit.recout.view.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.imageView4.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        playAnimation()

    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivImgWelcome, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.ivHeaderWelcome, View.ALPHA, 1f).setDuration(500)
        val img = ObjectAnimator.ofFloat(binding.ivImgWelcome, View.ALPHA, 1f).setDuration(500)
        val signin = ObjectAnimator.ofFloat(binding.imageView2, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.imageView4, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(title, img, signin, signup)
            start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.R

class SplashPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signIn_btn = findViewById<Button>(R.id.splash_btn_sign_in)
        val signUp_btn = findViewById<Button>(R.id.splash_btn_sign_up)

        signIn_btn.setOnClickListener {
            val intent = Intent(this, SignInPage::class.java)
            startActivity(intent)
        }

        signUp_btn.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
    }
}
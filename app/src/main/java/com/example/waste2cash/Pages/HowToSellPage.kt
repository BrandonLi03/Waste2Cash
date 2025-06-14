package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.R
import com.example.waste2cash.databinding.ActivityHowToSellPageBinding
import com.example.waste2cash.databinding.ActivityProfilePageBinding

class HowToSellPage : AppCompatActivity() {
    private lateinit var binding: ActivityHowToSellPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHowToSellPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back_btn = binding.backBtn

        back_btn.setOnClickListener {
            finish()
        }
    }
}
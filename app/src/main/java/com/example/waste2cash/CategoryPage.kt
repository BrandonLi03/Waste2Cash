package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.databinding.ActivityCategoryPageBinding

class CategoryPage : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paper_btn = binding.categoryBtnPaper
        val glass_btn = binding.categoryBtnGlassBottle
        val metal_btn = binding.categoryBtnMetal
        val plastic_btn = binding.categoryBtnPlastic
        val oil_btn = binding.categoryBtnOil

        val userId = intent.getIntExtra("userId", -1)

        paper_btn.setOnClickListener {
            Log.d("DEBUG", "Paper Clicked")
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("categoryId", 1)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        glass_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("categoryId", 2)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        metal_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("categoryId", 4)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        plastic_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("categoryId", 3)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        oil_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("categoryId", 5)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }
}
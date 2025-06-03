package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val greetings = binding.homeTvGreetings
        val profile_btn = binding.homeBtnProfile
        val money_btn = binding.homeBtnMoney
        val paper_btn = binding.homeBtnPaper
        val glassBottle_btn = binding.homeBtnGlassBottle
        val plastic_btn = binding.homeBtnPlastic
        val more_btn = binding.homeBtnMore
        val howToSell_btn = binding.homeBtnHowToSell

        val username = intent.getStringExtra("username")
        val userId = intent.getIntExtra("userId", -1)
        Log.d("DEBUG", "UserId : $userId")
        Log.d("DEBUG", "username : $username")

        greetings.text = "Hello, " +  username
        val money = databaseHelper.getMoney(userId)
        if(money != null) {
            money_btn.text = "Rp." + money.toString()
        }

        profile_btn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        paper_btn.setOnClickListener {
            val intent =  Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", "1")
            startActivity(intent)
        }

        glassBottle_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", "2")
            startActivity(intent)
        }

        plastic_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", "3")
            startActivity(intent)
        }

        more_btn.setOnClickListener {
            val intent = Intent(this, CategoryPage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        howToSell_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            startActivity(intent)
        }
    }
}
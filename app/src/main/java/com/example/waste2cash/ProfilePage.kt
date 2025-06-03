package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.databinding.ActivityProfilePageBinding

class ProfilePage : AppCompatActivity() {

    private lateinit var binding: ActivityProfilePageBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        databaseHelper = DatabaseHelper(this)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userId", -1)
        val username = binding.profileBtnName
        val phoneNumber = binding.profileBtnPhone
        val address = binding.profileBtnAddress
        val logOut_btn = binding.btnLogout

        val list = databaseHelper.readUser()
        val user = list.find { it.userId == userId }
        if (user!=null){
            username.text = user.username
            phoneNumber.text = user.phoneNumber.toString()
            address.text = user.address
        }

        logOut_btn.setOnClickListener{
            val intent = Intent(this, SplashPage::class.java)
            startActivity(intent)
        }
    }
}
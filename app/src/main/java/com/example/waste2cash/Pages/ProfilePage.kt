package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.Fragments.address_popup
import com.example.waste2cash.Fragments.phoneNumber_popup
import com.example.waste2cash.Fragments.username_popup
import com.example.waste2cash.databinding.ActivityProfilePageBinding

class ProfilePage : AppCompatActivity() {

    private lateinit var binding: ActivityProfilePageBinding
    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        databaseHelper = DatabaseHelper(this)
        setContentView(binding.root)

        userId = intent.getIntExtra("userId", -1)

        loadUserData()

        val username_btn = binding.profileBtnName
        val phoneNumber_btn = binding.profileBtnPhone
        val address_btn = binding.profileBtnAddress
        val logOut_btn = binding.btnLogout
        val back_btn = binding.backBtn

        address_btn.hint = "please fill up your address"

        username_btn.setOnClickListener{
            val showPupUp = username_popup()

            val bundle = Bundle()
            bundle.putInt("userId", userId)
            showPupUp.arguments = bundle

            showPupUp.show(supportFragmentManager, "showPopUp")

            Log.d("DEBUG", "userId:$userId")
        }

        address_btn.setOnClickListener{
            val showPupUp = address_popup()

            val bundle = Bundle()
            bundle.putInt("userId", userId)
            showPupUp.arguments = bundle

            showPupUp.show(supportFragmentManager, "showPopUp")

            Log.d("DEBUG", "userId:$userId")
        }

        phoneNumber_btn.setOnClickListener{
            val showPupUp = phoneNumber_popup()

            val bundle = Bundle()
            bundle.putInt("userId", userId)
            showPupUp.arguments = bundle

            showPupUp.show(supportFragmentManager, "showPopUp")

            Log.d("DEBUG", "userId:$userId")
        }

        logOut_btn.setOnClickListener{
            val intent = Intent(this, SplashPage::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

    private fun loadUserData() {
        try {
            val list = databaseHelper.readUser()
            val user = list.find { it.userId == userId }

            if (user != null) {
                binding.profileBtnName.text = user.username
                binding.profileBtnPhone.text = user.phoneNumber
                binding.profileBtnAddress.text = user.address

                Log.d("ProfilePage", "Data loaded for user: ${user.username}")
            } else {
                Log.e("ProfilePage", "User not found with userId: ${userId}")
            }
        } catch (e: Exception) {
            Log.e("ProfilePage", "Error loading user data: ${e.message}")
            e.printStackTrace()
        }
    }
}
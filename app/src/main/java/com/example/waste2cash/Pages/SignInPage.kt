package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.R

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_page)

        val inputphoneNumber = findViewById<EditText>(R.id.signIn_et_phoneNumber)
        val inputpassword = findViewById<EditText>(R.id.signIn_et_password)
        val signIn_btn = findViewById<Button>(R.id.signIn_btn_signin)
        val signIn_tv_signUp = findViewById<TextView>(R.id.signIn_tv_signup)
        val databaseHelper = DatabaseHelper(this)
        val back_btn = findViewById<Button>(R.id.back_btn)

        val vendorPhoneNumber = "0123"
        val vendorPassword = "kertas123"
        val vendorUsername = "Vendor Kertas"

        val allUsers = databaseHelper.readUser()
        val vendorExists = allUsers.any { it.phoneNumber == vendorPhoneNumber && it.role == "vendor" && it.category == "Paper"}

        if (!vendorExists) {
            databaseHelper.insertUser(vendorUsername, vendorPhoneNumber, vendorPassword, "vendor", "Paper")
        }

        signIn_btn.setOnClickListener {
            val phoneNumber = inputphoneNumber.text.toString()
            val password = inputpassword.text.toString()
            val list = databaseHelper.readUser()

            if (phoneNumber == null || password == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = list.find { it.phoneNumber == phoneNumber && it.password == password }

            if (user != null) {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()

                val intent: Intent
                if (user.role == "vendor") {
                    intent = Intent(this, HomeAdminPage::class.java)
                } else {
                    intent = Intent(this, HomePage::class.java)
                }

                intent.putExtra("userId", user.userId)
                intent.putExtra("username", user.username)
                startActivity(intent)
                finish()
            } else {
                val accountExists = list.any { it.phoneNumber == phoneNumber }
                if (!accountExists) {
                    Toast.makeText(this, "Account didn't exist", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signIn_tv_signUp.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
            finish()
        }

        back_btn.setOnClickListener{
            val intent = Intent(this, SplashPage::class.java)
            startActivity(intent)
        }
    }
}
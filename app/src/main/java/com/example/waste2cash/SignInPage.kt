package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.Database.DatabaseHelper

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

        signIn_btn.setOnClickListener {
            val phoneNumber = inputphoneNumber.text.toString().toInt()
            val password = inputpassword.text.toString()
            val list = databaseHelper.readUser()

            if (phoneNumber == null || password == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = list.find { it.phoneNumber == phoneNumber && it.password == password }
            if (user != null) {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
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
    }
}
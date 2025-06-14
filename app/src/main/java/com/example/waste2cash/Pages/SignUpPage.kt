package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.R

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_page)

        val inputUsername = findViewById<EditText>(R.id.signUp_et_username)
        val inputphoneNumber = findViewById<EditText>(R.id.signUp_et_phoneNumber)
        val inputPassword = findViewById<EditText>(R.id.signUp_et_password)
        val signUp_btn = findViewById<Button>(R.id.signUp_btn_signup)
        val databaseHelper = DatabaseHelper(this)
        val back_btn = findViewById<Button>(R.id.back_btn)

        signUp_btn.setOnClickListener {
            val username = inputUsername.text.toString()
            val phoneNumber = inputphoneNumber.text.toString()
            val password = inputPassword.text.toString()
            val role = "user"
            val category = "none"

            if (username == "" || phoneNumber == null || password == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                inputPassword.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }else{
                Log.d("DEBUG", "username=$username, phoneNumber=$phoneNumber, password=$password")

                databaseHelper.insertUser(username, phoneNumber, password, role, category)
                val intent =  Intent(this, SignInPage::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
        }

        back_btn.setOnClickListener{
            val intent = Intent(this, SplashPage::class.java)
            startActivity(intent)
        }
    }
}
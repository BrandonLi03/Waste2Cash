package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.Model.User

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_page)

        val inputUsername = findViewById<EditText>(R.id.signUp_et_username)
        val inputEmail = findViewById<EditText>(R.id.signUp_et_email)
        val inputPassword = findViewById<EditText>(R.id.signUp_et_password)
        val signUp_btn = findViewById<Button>(R.id.signUp_btn_signup)
        val databaseHelper = DatabaseHelper(this)


        signUp_btn.setOnClickListener {
            val username = inputUsername.text.toString()
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()


            if (email == "" || password == "" || username == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                inputPassword.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }else{
//                Log.d("DEBUG", "username=$username, email=$email, password=$password")

                databaseHelper.insertUser(username, email, password)
                val intent =  Intent(this, SignInPage::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
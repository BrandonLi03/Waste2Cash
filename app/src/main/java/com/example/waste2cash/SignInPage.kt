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

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_page)

        val databaseHelper = DatabaseHelper(this)
        val inputemail = findViewById<EditText>(R.id.signIn_et_email)
        val inputpassword = findViewById<EditText>(R.id.signIn_et_password)
        val signIn_btn = findViewById<Button>(R.id.signIn_btn_signin)

        signIn_btn.setOnClickListener {
            val email = inputemail.text.toString()
            val password = inputpassword.text.toString()
            val list = databaseHelper.readUser()
//            for (user in list) {
//                Log.d("DATABASE_CHECK", "Email: ${user.email}, Password: ${user.password}")
//            }

            if (email == "" || password == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = list.find { it.email == email && it.password == password }
            if (user != null) {
                Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("username", user.username)
                startActivity(intent)
                finish()
            } else {
                val emailExists = list.any { it.email == email }
                if (!emailExists) {
                    Toast.makeText(this, "Account didn't exist", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
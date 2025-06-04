package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.databinding.ActivityFormPageBinding

class FormPage : AppCompatActivity() {

    private lateinit var binding: ActivityFormPageBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        binding = ActivityFormPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userId", -1)
        val categoryId = intent.getIntExtra("categoryId", -1)
        Log.d("DEBUG", "userId=$userId, categoryId=$categoryId")
        val inputDateTime = binding.formEtDateTime
        val inputWeight = binding.formEtWeight
        val request_btn = binding.btnSendReq
        val back_btn = binding.backBtn

        request_btn.setOnClickListener {
            val dateTime = inputDateTime.text.toString()
            val weightStr = inputWeight.text.toString()
            if (weightStr.isEmpty() || dateTime.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                val weight = weightStr.toInt()
                databaseHelper.insertTransaction(userId, categoryId, weight, dateTime)
                Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show()
                Log.d("DEBUG", "userId=$userId, categoryId=$categoryId, weight=$weight, dateTime=$dateTime")
                databaseHelper.updateUserMoney(userId, categoryId, weight)
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("userId", userId)
                startActivity(intent)
                finish()
            }
        }

        back_btn.setOnClickListener{
            val intent = Intent(this, CategoryPage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }
}
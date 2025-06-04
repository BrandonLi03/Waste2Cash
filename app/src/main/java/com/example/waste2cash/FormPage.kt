package com.example.waste2cash

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.Model.Category

class FormPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_page)

        val dateEt = findViewById<EditText>(R.id.et_date)
        val timeEt = findViewById<EditText>(R.id.et_time)
        val addressEt = findViewById<EditText>(R.id.et_address)
        val quantityEt = findViewById<EditText>(R.id.et_quantity)
        val sendBtn = findViewById<Button>(R.id.btn_request)

        val dbHelper = DatabaseHelper(this)

        sendBtn.setOnClickListener {
            val date = dateEt.text.toString().trim()
            val time = timeEt.text.toString().trim()
            val address = addressEt.text.toString().trim()
            val quantity = quantityEt.text.toString().toIntOrNull()
            val category = intent.getStringExtra("category") ?: ""
            val userId = intent.getIntExtra("userId", -1)

            if (date.isEmpty() || time.isEmpty() || address.isEmpty() || quantity == null) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dbHelper.insertTransaction(userId, category, quantity,date, time, address)

            Toast.makeText(this, "Transaction saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

package com.example.waste2cash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waste2cash.Adapter.CategoryAdapter
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.Model.Category
import com.example.waste2cash.databinding.ActivityHomePageBinding
import org.w3c.dom.Text

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnHowToSell : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        recyclerView= binding.rvCategory
        setContentView(binding.root)

        setUpRecycler()
        val userName = binding.tvGreetings
        val profile_btn = binding.btnProfile
        val categories_btn = binding.btnCategories
        val btnHowToSell = binding.btnHowToSell

        val username = intent.getStringExtra("username")
        val userId = intent.getIntExtra("userId", -1)
        Log.d("DEBUG", "UserId : $userId")
        Log.d("DEBUG", "username : $username")

        userName.text = "Hello, " +  username
        profile_btn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        categories_btn.setOnClickListener {
            val intent = Intent(this, CategoryPage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        btnHowToSell.setOnClickListener {
            val intent = Intent(this, HowToSellPage::class.java)
            startActivity(intent)
        }

    }

    private fun setUpRecycler(){
        val arrayList = databaseHelper.readCategory()
        categoryAdapter = CategoryAdapter(arrayList, object: CategoryAdapter.OnItemClickListener{
            override fun onItemClick(item: Category) {
                val intent = Intent(this@HomePage, CategoryPage::class.java)
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = categoryAdapter
    }
}
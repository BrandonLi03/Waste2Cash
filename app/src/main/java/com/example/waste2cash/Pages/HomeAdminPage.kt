package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waste2cash.Adapter.PickupRequestAdapter
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.R
import com.example.waste2cash.databinding.ActivityHomeAdminPageBinding
import com.example.waste2cash.databinding.ActivityHomePageBinding

class HomeAdminPage : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminPageBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var pickupRequestRecyclerView: RecyclerView
    private lateinit var pickupRequestAdapter: PickupRequestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        binding = ActivityHomeAdminPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val greetings = binding.homeTvGreetings
        val profile_btn = binding.homeBtnProfile
        val category = binding.tvVendor
        var jenisVendor = ""
        var username = ""

        val userId = intent.getIntExtra("userId", -1)
        val list = databaseHelper.readUser()
        val user = list.find { it.userId == userId }
        if (user!=null){
            username = user.username
            jenisVendor = user.category
        }
        Log.d("DEBUG", "UserId : $userId")
        Log.d("DEBUG", "username : $username")
        Log.d("DEBUG", "Jenis Vendor: $jenisVendor")

        greetings.text = "Hello, " +  username
        category.text = "Vendor " + jenisVendor

        profile_btn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        pickupRequestRecyclerView = binding.rvRequest
        pickupRequestRecyclerView.layoutManager = LinearLayoutManager(this)

        val pickupRequests = databaseHelper.getAllPickupRequestsForAdmin(jenisVendor)

        pickupRequestAdapter = PickupRequestAdapter(pickupRequests)
        pickupRequestRecyclerView.adapter = pickupRequestAdapter

        if (pickupRequests.isEmpty()) {
            Toast.makeText(this, "Tidak ada permintaan untuk kategori ini.", Toast.LENGTH_LONG).show()
        }
    }
}
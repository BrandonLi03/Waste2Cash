    package com.example.waste2cash

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

    class CategoryPage : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_category_page)

            val btnPaper = findViewById<Button>(R.id.btn_category1)

            val userId = intent.getIntExtra("userId", -1)

            btnPaper.setOnClickListener {
                val intent = Intent(this, FormPage::class.java)
                intent.putExtra("category", "paper")
                intent.putExtra("userId", userId)
                startActivity(intent)
            }
        }
    }
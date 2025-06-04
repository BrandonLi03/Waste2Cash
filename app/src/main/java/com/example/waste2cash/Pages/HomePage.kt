package com.example.waste2cash.Pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.databinding.ActivityHomePageBinding
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val greetings = binding.homeTvGreetings
        val profile_btn = binding.homeBtnProfile
        val money_btn = binding.homeBtnMoney
        val paper_btn = binding.homeBtnPaper
        val glassBottle_btn = binding.homeBtnGlassBottle
        val plastic_btn = binding.homeBtnPlastic
        val more_btn = binding.homeBtnMore
        val howToSell_btn = binding.homeBtnHowToSell
        val news_rv = binding.homeRvNews
        var username = ""

        val userId = intent.getIntExtra("userId", -1)
        val list = databaseHelper.readUser()
        val user = list.find { it.userId == userId }
        if (user!=null){
            username = user.username
        }
        Log.d("DEBUG", "UserId : $userId")
        Log.d("DEBUG", "username : $username")

        greetings.text = "Hello, " +  username
        val money = databaseHelper.getMoney(userId)
        if(money != null) {
            money_btn.text = "Rp." + money.toString()
        }

        profile_btn.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        paper_btn.setOnClickListener {
            val intent =  Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", 1)
            startActivity(intent)
        }

        glassBottle_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", 2)
            startActivity(intent)
        }

        plastic_btn.setOnClickListener {
            val intent = Intent(this, FormPage::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("categoryId", 3)
            startActivity(intent)
        }

        more_btn.setOnClickListener {
            val intent = Intent(this, CategoryPage::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        howToSell_btn.setOnClickListener {
            val intent = Intent(this, HowToSellPage::class.java)
            startActivity(intent)
        }
    }


    fun getNews() {
        val newsApiClient = NewsApiClient("66d1125824cc46b2a757c2c49c4f1f6b")

        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .language("en")
                .build(),
            object : NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    response.articles.forEach { article ->
                        Log.i("Article", article.title)
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.i("GOT Failure", throwable.message ?: "Unknown error")
                }
            }
        )
    }
}
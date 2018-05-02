package com.artimanton.coinmarketcap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.artimanton.coinmarketcap.adapters.CoinAdapter
import com.artimanton.coinmarketcap.api.ServiceGenerator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var adapter:CoinAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter=CoinAdapter()
        recyclerView.adapter=adapter

        loadCoins()
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            loadCoins()
            Toast.makeText(this@MainActivity, "Обновление денных", Toast.LENGTH_LONG).show()
        }, 5000)

    }

    private fun loadCoins(){
        launch(UI) {
            try {
                adapter.mData = ServiceGenerator.serverApi.loadData(10).await()
            } catch (ex: Exception) {
                Toast.makeText(this@MainActivity, R.string.no_connection, Toast.LENGTH_LONG).show()
            }
        }
    }


}

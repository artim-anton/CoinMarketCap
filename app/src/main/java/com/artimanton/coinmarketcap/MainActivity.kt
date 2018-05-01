package com.artimanton.coinmarketcap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.artimanton.coinmarketcap.adapters.CoinAdapter
import com.artimanton.coinmarketcap.api.ServiceGenerator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

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

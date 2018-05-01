package com.artimanton.coinmarketcap.api

import com.artimanton.coinmarketcap.models.CoinCap
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.zip.Deflater

interface ServerApi {
    @GET( "ticker")
    fun loadData(@Query("limit") limit:Int): Deferred<List<CoinCap>>
}
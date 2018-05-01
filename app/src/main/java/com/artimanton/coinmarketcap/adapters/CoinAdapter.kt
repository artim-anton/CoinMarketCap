package com.artimanton.coinmarketcap.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.artimanton.coinmarketcap.R
import com.artimanton.coinmarketcap.components.MyViewHolder
import com.artimanton.coinmarketcap.models.CoinCap
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinAdapter:RecyclerView.Adapter<MyViewHolder>(){
    var mData:List<CoinCap>?=null
    set(value){
        field=value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false))
    }

    override fun getItemCount(): Int = mData?.size?:0

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mData?.let {
            val coin=it[position]
            holder.view.tvSymbol.text=coin.symbol
            holder.view.tvName.text=coin.name
            holder.view.tvPrice.text="$${coin.price_usd}"
            holder.view.tvPriceChange.text= if (coin.percent_change_24h > 0)
                                            {       holder.view.tvPriceChange.setTextColor(Color.GREEN)
                                                    "+${coin.percent_change_24h}%"}
                                            else {
                                                    holder.view.tvPriceChange.setTextColor(Color.RED)
                                                    "${coin.percent_change_24h}%"

                                            }


        }
    }

}
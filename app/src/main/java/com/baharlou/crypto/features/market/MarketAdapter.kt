package com.baharlou.crypto.features.market

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baharlou.crypto.R
import com.baharlou.crypto.apiManager.BASE_URL_IMAGE
import com.baharlou.crypto.apiManager.model.CoinsData
import com.baharlou.crypto.databinding.ItemRecyclerMarketBinding
import com.bumptech.glide.Glide

class MarketAdapter(
    private var data: ArrayList<CoinsData.Data>,
    private val recyclerCallback: RecyclerCallback
) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    lateinit var binding: ItemRecyclerMarketBinding

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindViews(dataCoin: CoinsData.Data) {

            if (dataCoin.dISPLAY != null && dataCoin.rAW != null) {
                binding.txtCoinName.text = dataCoin.coinInfo.fullName
                binding.txtPrice.text = dataCoin.dISPLAY.uSD.pRICE

                val change = dataCoin.rAW.uSD.cHANGEPCT24HOUR
                if (change > 0) {
                    binding.txtMarketName.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorGain
                        )
                    )
                    binding.txtMarketName.text =
                        dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 4) + "%"
                } else if (change < 0) {
                    binding.txtMarketName.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorLoss
                        )
                    )
                    binding.txtMarketName.text =
                        dataCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 5) + "%"
                } else {
                    binding.txtMarketName.text = "0%"
                }

                val marketCap = dataCoin.rAW.uSD.mKTCAP / 1000000000
                val indexDot = marketCap.toString().indexOf('.')
                binding.txtMarketcap.text =
                    "$" + marketCap.toString().substring(0, indexDot + 3) + " B"

                try {
                    Glide
                        .with(itemView)
                        .load(BASE_URL_IMAGE + dataCoin.coinInfo.imageUrl)
                        .into(binding.imgItem)
                }
                catch (ex:Exception){
                    Log.e("glideErr123 ", "bindViews: glide error:${ex.message}", )
                }

                itemView.setOnClickListener {
                    recyclerCallback.onCoinItemClicked(dataCoin)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerMarketBinding.inflate(inflater, parent, false)

        return MarketViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface RecyclerCallback {
        fun onCoinItemClicked(dataCoin: CoinsData.Data)
    }

}
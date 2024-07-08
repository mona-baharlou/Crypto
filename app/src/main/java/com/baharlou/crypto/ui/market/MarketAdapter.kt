package com.baharlou.crypto.ui.market

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baharlou.crypto.R
import com.baharlou.crypto.databinding.ItemRecyclerMarketBinding
import com.baharlou.crypto.model.BASE_URL_IMAGE
import com.baharlou.crypto.model.data.coin.Data
import com.bumptech.glide.Glide


class MarketAdapter(
    private var data: ArrayList<Data>,
    private val recyclerCallback: RecyclerCallback
) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    lateinit var binding: ItemRecyclerMarketBinding
    //@Inject lateinit var imageHelper: ImageHelper

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


       /* init {
            resolveDependency(itemView)
        }
*/

       /* private fun resolveDependency(view: View) {
            val hiltEntryPoint = fromView(
                view,
                MarketViewHolder::class.java
            )
            imageHelper = hiltEntryPoint.imageHelper
        }*/

        @SuppressLint("SetTextI18n")
        fun bindViews(dataCoin: Data) {

            if (dataCoin.DISPLAY != null && dataCoin.RAW != null) {
                binding.txtCoinName.text = dataCoin.CoinInfo!!.FullName
                binding.txtPrice.text = dataCoin.RAW!!.USD!!.PRICE

                val change = dataCoin.RAW!!.USD!!.CHANGE24HOUR
                if (change!!.toInt() > 0) {
                    binding.txtMarketName.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorGain
                        )
                    )
                    binding.txtMarketName.text =
                        dataCoin.RAW?.USD?.CHANGEPCT24HOUR.toString().substring(0, 4) + "%"
                } else if (change.toInt() < 0) {
                    binding.txtMarketName.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorLoss
                        )
                    )
                    binding.txtMarketName.text =
                        dataCoin.RAW?.USD?.CHANGEPCT24HOUR.toString().substring(0, 5) + "%"
                } else {
                    binding.txtMarketName.text = "0%"
                }

                val marketCap = dataCoin.RAW?.USD?.MARKET!!.toInt() / 1000000000
                val indexDot = marketCap.toString().indexOf('.')
                binding.txtMarketcap.text =
                    "$" + marketCap.toString().substring(0, indexDot + 3) + " B"

               /* try {
                    imageHelper?.glide!!
                        //.with(itemView)
                        .load(BASE_URL_IMAGE + dataCoin.CoinInfo?.ImageUrl)
                        .into(binding.imgItem)

                } catch (ex: Exception) {

                }
*/
                try {
                    Glide
                        .with(itemView)
                        .load(BASE_URL_IMAGE + dataCoin.CoinInfo?.ImageUrl)
                        .into(binding.imgItem)
                } catch (ex: Exception) {
                    Log.e("glideErr123 ", "bindViews: glide error:${ex.message}")
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
        fun onCoinItemClicked(dataCoin: Data)
    }


/*
    @EntryPoint
    @InstallIn(ViewComponent::class)
    interface MarketViewHolderEntryPoint {
        fun imageHelper(): ImageHelper
    }
*/


}



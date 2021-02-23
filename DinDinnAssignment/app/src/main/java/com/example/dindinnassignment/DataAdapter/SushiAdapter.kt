/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.DataAdapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dindinnassignment.Models.SushiModel
import com.example.dindinnassignment.R
import com.rilixtech.materialfancybutton.MaterialFancyButton
import java.util.*
import kotlin.concurrent.schedule

class SushiAdapter (private val sushiOperator: SushiOperationListener,val context : Context?) :
    RecyclerView.Adapter<SushiAdapter.SushiViewHolder>() {
    private val sushies = mutableListOf<SushiModel>()

    fun setSushies(sushies: List<SushiModel>) {
        this.sushies.clear()
        this.sushies.addAll(sushies)
        notifyDataSetChanged()
    }

    interface SushiOperationListener {
        fun addToCart(sushiID: Long)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SushiAdapter.SushiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_viewholder_layout, parent, false)
        return SushiViewHolder(view)
    }

    override fun onBindViewHolder(holder: SushiAdapter.SushiViewHolder, position: Int) {
        val sushi = sushies[position]
        Glide
            .with(holder.itemView)
            .load(sushi.imageUrl)
            .centerCrop()
            .into(holder.mFoodImage)

        holder.mFTitle.text=sushi.title
        holder.mFDiscriptipn.text=sushi.decription
        holder.mFSmallDesc.text=sushi.miniDescription
        holder.mFTitle.text=sushi.title
        holder.mBtnAddCart.setText("${sushi.price} USD")

        holder.mBtnAddCart.setOnClickListener{
            Log.e("Clicked","yes")
           sushiOperator.addToCart(sushiID = sushi._id)


            Log.e("Clicked", "yes")
            holder.mBtnAddCart.setText("added + 1")


            Timer("SettingUp", false).schedule(500) {
                (context as Activity).runOnUiThread {
                    holder.mBtnAddCart.setText("${sushi.price} USD")

                }
            }
//            Timer("SettingUp", false).schedule(500) {
//
//            }
        }
    }

    override fun getItemCount() = sushies.size

    inner class SushiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mFoodImage: ImageView = itemView.findViewById(R.id.mFoodImage)
        val mFTitle: TextView = itemView.findViewById(R.id.mFTitle)
        val mFDiscriptipn: TextView = itemView.findViewById(R.id.mFDiscriptipn)
        val mFSmallDesc: TextView = itemView.findViewById(R.id.mFSmallDesc)
        val mBtnAddCart: MaterialFancyButton = itemView.findViewById(R.id.mBtnAddCart)
    }

}
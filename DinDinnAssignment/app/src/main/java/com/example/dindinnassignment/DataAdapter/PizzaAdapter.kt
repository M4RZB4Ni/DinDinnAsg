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
import com.example.dindinnassignment.Models.PizzaModel
import com.example.dindinnassignment.R
import com.rilixtech.materialfancybutton.MaterialFancyButton
import java.util.*
import kotlin.concurrent.schedule


class PizzaAdapter(private val pizzaOperator: PizzaOperationListener, val context: Context?) :
    RecyclerView.Adapter<PizzaAdapter.FoodViewHolder>() {
    private val pizzas = mutableListOf<PizzaModel>()






    fun setPizzas(pizzas: List<PizzaModel>) {
        this.pizzas.clear()
        this.pizzas.addAll(pizzas)
        notifyDataSetChanged()
    }

    interface PizzaOperationListener {

        fun addToCart(pizzaID: Long)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaAdapter.FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_viewholder_layout, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaAdapter.FoodViewHolder, position: Int) {
        val pizza = pizzas[position]
        Glide
            .with(holder.itemView)
            .load(pizza.imageUrl)
            .centerCrop()
            .into(holder.mFoodImage)

        holder.mFTitle.text=pizza.title
        holder.mFDiscriptipn.text=pizza.decription
        holder.mFSmallDesc.text=pizza.miniDescription
        holder.mFTitle.text=pizza.title
        holder.mBtnAddCart.setText("${pizza.price} USD")
        holder.mBtnAddCart.setOnClickListener{
            Log.e("Clicked", "yes")
            holder.mBtnAddCart.setText("added + 1")


            Timer("SettingUp", false).schedule(500) {
                (context as Activity).runOnUiThread {
                    holder.mBtnAddCart.setText("${pizza.price} USD")

                }
            }
//            Timer("SettingUp", false).schedule(500) {
//
//            }
            pizzaOperator.addToCart(pizzaID = pizza.id)

        }
    }

    override fun getItemCount() = pizzas.size

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mFoodImage: ImageView = itemView.findViewById(R.id.mFoodImage)
        val mFTitle: TextView = itemView.findViewById(R.id.mFTitle)
        val mFDiscriptipn: TextView = itemView.findViewById(R.id.mFDiscriptipn)
        val mFSmallDesc: TextView = itemView.findViewById(R.id.mFSmallDesc)
        val mBtnAddCart: MaterialFancyButton = itemView.findViewById(R.id.mBtnAddCart)
    }
}
/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.Models


import com.google.gson.annotations.SerializedName

data class PizzaModel(
    @SerializedName("_id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("decription")
    var decription: String,
    @SerializedName("miniDescription")
    var miniDescription: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("isAdded")
    var isAdded: Boolean

)
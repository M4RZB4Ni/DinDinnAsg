/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.Models

import com.google.gson.annotations.SerializedName

data class SushiModel(
    @SerializedName("_id")
    val _id:Long,
    @SerializedName("title")
    val title:String,
    @SerializedName("decription")
    val decription:String,
    @SerializedName("miniDescription")
    val miniDescription:String,
    @SerializedName("imageUrl")
    val imageUrl:String,
    @SerializedName("price")
    val price:Int,
    @SerializedName("isAdded")
    val isAdded:Boolean)
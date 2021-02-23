/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.Models

import com.google.gson.annotations.SerializedName

data class MainActivityData(
                            @SerializedName("Title")
                            val title: String = "",
                            @SerializedName("Menu")
                            val menu: List<String>?,
                            @SerializedName("SliderMenu")
                            val sliderMenu: List<String>?)
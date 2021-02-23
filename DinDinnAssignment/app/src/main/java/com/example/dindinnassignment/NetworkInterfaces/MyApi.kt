/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.NetworkInterfaces

import com.example.dindinnassignment.Models.MainActivityData
import com.example.dindinnassignment.Models.PizzaModel
import com.example.dindinnassignment.Models.SushiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @POST("Ads/Delivery1/")
    fun getMainData(): Observable<MainActivityData>

    @GET("GetPizzas.php/")
    fun getPizzaFromServer(): Observable<List<PizzaModel>>

    @GET("GetSushies.php/")
    fun getSushiFromServer(): Observable<List<SushiModel>>

}
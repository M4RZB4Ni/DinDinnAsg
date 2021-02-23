/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.NetworkInterfaces

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkHelper{

        companion object ServiceBuilderMain {
            private val client = OkHttpClient
                .Builder()
                .build()

            private val mainRetro = Retrofit.Builder()
                .baseUrl("http://jamq.ir:3000")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MyApi::class.java)

            fun apiAgentMain(): MyApi {
               return mainRetro
            }

            private val pizzaRetro = Retrofit.Builder()
                .baseUrl("http://dindinn.freecloudsite.com/DinDinnAPI/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MyApi::class.java)

            fun foodApiAgent(): MyApi {
                return pizzaRetro
            }


    }
}
/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.Repositories

import android.util.Log
import com.example.dindinnassignment.Models.SushiModel
import com.example.dindinnassignment.NetworkInterfaces.NetworkHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SushiRepo {

    private val sushies = mutableListOf<SushiModel>()

    var compositeDisposable : CompositeDisposable? =null

    fun getSushiDataServer() = Observable.fromCallable<List<SushiModel>>
    {
        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(
            NetworkHelper.foodApiAgent().getSushiFromServer()
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({response ->


                    sushies.addAll(response)
                    Log.e("SushidData", response.toString())

                }, {t -> onFailure(t) }))

        sushies
    }.subscribeOn(Schedulers.io())!!
    private fun onFailure(t: Throwable) {
        Log.e("ResponseRitroERR", t.message.toString())
    }



    fun addSushiToCard(sushiID: Long){
        val sushi = sushies.first { sushi -> sushi._id == sushiID }
        val indexof= sushies.indexOf(sushi);
            Log.e("indexofindexof", indexof.toString())

            sushies[indexof] = sushi.copy(isAdded = true)
        Log.e("AddPizzaToCard",sushi.toString())


    }

}
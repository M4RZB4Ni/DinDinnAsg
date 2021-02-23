/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.Repositories

import android.content.Context
import android.util.Log
import com.example.dindinnassignment.Models.PizzaModel
import com.example.dindinnassignment.NetworkInterfaces.NetworkHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PizzaRepo {
    private val pizzas = mutableListOf<PizzaModel>()


    var compositeDisposable : CompositeDisposable? =null

     fun getPizzaDataServer() = Observable.fromCallable<List<PizzaModel>>
     {
         compositeDisposable = CompositeDisposable()
         compositeDisposable!!.add(
             NetworkHelper.foodApiAgent().getPizzaFromServer()
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe({response ->   pizzas.addAll(response
                 )}, {t -> onFailure(t) }))

         pizzas
     }.subscribeOn(Schedulers.io())!!
    private fun onFailure(t: Throwable) {
        Log.e("ResponseRitroERR", t.message.toString())

    }







    fun addPizzaToCard(pizzaID: Long) {
        val pizza = pizzas.first { pizza -> pizza.id == pizzaID }
        val indexof= pizzas.indexOf(pizza);
        Log.e("indexofindexof", indexof.toString())

        pizzas[indexof] = pizza.copy(isAdded = true)
        Log.e("AddPizzaToCard",pizza.toString())
    }
}
/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*

import com.example.dindinnassignment.FoodState.PizzaListState
import com.example.dindinnassignment.Models.PizzaModel
import com.example.dindinnassignment.Repositories.PizzaRepo

class PizzaViewModel (
    initialState: PizzaListState,
    private val pizzaListRepo: PizzaRepo
) : BaseMvRxViewModel<PizzaListState>(initialState, debugMode = true) {


    val errMessage = MutableLiveData<String>()
    init {
        setState {
            copy(pizzas = Loading())
        }
        pizzaListRepo.getPizzaDataServer()
            .execute {
                Log.e("itititit",it.toString())
                copy(pizzas = it as Async<MutableList<PizzaModel>>)
            }
    }

    fun AddPizzaToCart(pizzaID: Long) {

        Log.e("AddToCartpizzaIDOne-->", pizzaID.toString())
        pizzaListRepo.addPizzaToCard(pizzaID)

    }

    companion object : MvRxViewModelFactory<PizzaViewModel, PizzaListState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: PizzaListState): PizzaViewModel? {
            return PizzaViewModel(state,PizzaRepo())
        }
    }
}
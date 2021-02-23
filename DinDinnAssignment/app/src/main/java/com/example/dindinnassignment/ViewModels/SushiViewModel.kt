/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.example.dindinnassignment.FoodState.SushiListState
import com.example.dindinnassignment.Repositories.SushiRepo


class SushiViewModel (
    initialState: SushiListState,
    private val sushiListRepo: SushiRepo
) : BaseMvRxViewModel<SushiListState>(initialState, debugMode = true) {


    val errMessage = MutableLiveData<String>()

    init {
        setState {
            copy(sushies = Loading())
        }
        sushiListRepo.getSushiDataServer()
            .execute {
                Log.e("Sushi_itititit",it.toString())

                copy(sushies = it)
            }
    }

    fun AddSushiToCart(sushiID: Long) {

        sushiListRepo.addSushiToCard(sushiID)

    }
    companion object : MvRxViewModelFactory<SushiViewModel, SushiListState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: SushiListState
        ): SushiViewModel? {
            return SushiViewModel(state, SushiRepo())
        }
    }


}
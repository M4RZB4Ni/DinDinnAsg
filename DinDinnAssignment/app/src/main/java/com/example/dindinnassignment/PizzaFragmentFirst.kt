/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.andremion.counterfab.CounterFab
import com.example.dindinnassignment.DataAdapter.PizzaAdapter
import com.example.dindinnassignment.ViewModels.PizzaViewModel
import kotlinx.android.synthetic.main.fragment_pizza__first.*


class PizzaFragmentFirst : BaseMvRxFragment() {


    private lateinit var pizzaAdapter: PizzaAdapter

    private val pizzaListViewModel: PizzaViewModel by activityViewModel()




    override fun invalidate() {
        withState(pizzaListViewModel) { state ->
            when (state.pizzas) {
                is Loading -> {
                    // progress_barm.visibility = View.VISIBLE
                    mPizzaRecy.visibility = View.GONE
                }
                is Success -> {
                    //  progress_barm.visibility = View.GONE
                    mPizzaRecy.visibility = View.VISIBLE
                    pizzaAdapter.setPizzas(state.pizzas.invoke())
                }
                is Fail -> {
                    Toast.makeText(requireContext(), "Failed to load all Foods", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pizza__first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pizzaAdapter = PizzaAdapter(object : PizzaAdapter.PizzaOperationListener {
            override fun addToCart(pizzaID: Long) {
                val btn = activity!!.findViewById<CounterFab>(R.id.mCart)
                btn.increase()
                pizzaListViewModel.AddPizzaToCart(pizzaID)
            }


        }, context)
        mPizzaRecy.adapter = pizzaAdapter
        mPizzaRecy.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        mPizzaRecy.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
        pizzaListViewModel.errMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        })
    }



}
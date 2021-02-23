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
import com.example.dindinnassignment.DataAdapter.SushiAdapter
import com.example.dindinnassignment.ViewModels.SushiViewModel

import kotlinx.android.synthetic.main.fragment_sushi.*


class SushiFragment : BaseMvRxFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var sushiAdapter: SushiAdapter

    private val sushiViewModel: SushiViewModel by activityViewModel()



    override fun invalidate() {
        withState(sushiViewModel) { state ->
            when (state.sushies) {
                is Loading -> {
                    // progress_barm.visibility = View.VISIBLE
                    mSushiRecy.visibility = View.GONE
                }
                is Success -> {
                    //  progress_barm.visibility = View.GONE
                    mSushiRecy.visibility = View.VISIBLE
                    sushiAdapter.setSushies(state.sushies.invoke())
                }
                is Fail -> {
                    Toast.makeText(requireContext(), "Failed to load all Sushies", Toast.LENGTH_SHORT).show()
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
        return inflater.inflate(R.layout.fragment_sushi, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sushiAdapter = SushiAdapter(object : SushiAdapter.SushiOperationListener {
            override fun addToCart(sushiID: Long) {
                val btn=activity!!.findViewById<CounterFab>(R.id.mCart)
                btn.increase()
                sushiViewModel.AddSushiToCart(sushiID)
            }


        },context)
        mSushiRecy.adapter = sushiAdapter

        mSushiRecy.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        mSushiRecy.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        sushiViewModel.errMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        })
    }


}
/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dindinnassignment.Models.MainActivityData
import com.example.dindinnassignment.NetworkInterfaces.NetworkHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pizza__first.*
import kotlinx.android.synthetic.main.fragment_sushi.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.tabs
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.view.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import render.animations.Fade
import render.animations.Render


class MainActivity : AppCompatActivity() {


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    var outUp :Render?=null
    var inUp :Render?=null
    var outDown :Render?=null
    var inDown :Render?=null
    private var compositeDisposable : CompositeDisposable? =null
   private fun showImages(slideOne:String,  slideTwo :String,  slideThree:String)
    {
        val carousel: ImageCarousel = findViewById(R.id.carousel)

        val list = mutableListOf<CarouselItem>()

// Image URL with caption
        list.add(
            CarouselItem(
                imageUrl = slideOne
            )
        )

// Just image URL
        list.add(
            CarouselItem(
                imageUrl = slideTwo
            )
        )

// Image drawable with caption
        list.add(
            CarouselItem(
                imageUrl = slideThree
            )
        )





        carousel.addData(list)

    }

    private fun animateInit()
    {
        outUp = Render(this)
        inDown = Render(this)
        inUp = Render(this)
        outDown = Render(this)

        outUp!!.setAnimation(Fade().OutUp(mTitle))
        inDown!!.setAnimation(Fade().InDown(mTitle))
        inUp!!.setAnimation(Fade().InUp(mCart))
        outDown!!.setAnimation(Fade().InUp(mCart))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        animateInit()
        initBottomMenu()
        getMainDataServer()
        initCartFab()




    }

   private fun initCartFab()
    {
        mCart.setOnClickListener {
            startActivity(Intent(this,Cart::class.java))
        }
    }

    private fun initBottomMenu()
    {
        tabs.setupWithViewPager(viewPager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabs.setBackgroundColor(resources.getColor(R.color.white,this.theme))
        }else{
            tabs.setBackgroundColor(resources.getColor(R.color.white))

        }
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Toast.makeText(
                            this@MainActivity,
                            "STATE_COLLAPSED",
                            Toast.LENGTH_SHORT
                        ).show()
                        mCart.visibility=View.INVISIBLE

                        inDown?.start()
                        outDown?.start()
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Toast.makeText(
                            this@MainActivity,
                            "STATE_EXPANDED",
                            Toast.LENGTH_SHORT
                        ).show()
                        mCart.visibility=View.VISIBLE
                            inUp?.start()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> outUp?.start()
                    //BottomSheetBehavior.STATE_SETTLING ->

                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(
                        this@MainActivity,
                        "STATE_HIDDEN",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(this@MainActivity, "OTHER_STATE", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }



    private fun getMainDataServer()
    {

        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(
             NetworkHelper.apiAgentMain().getMainData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
    }

    private fun onFailure(t: Throwable) {
        Log.e("ResponseRitroERRf", t.message.toString())

    }

    private fun onResponse(response: MainActivityData) {
        //progress_bar.visibility = View.GONE
        mTitle.text =response.title
        showImages(response.sliderMenu!![0], response.sliderMenu[1], response.sliderMenu[2])
        initPager(response.menu!![1],response.menu[0],response.menu[2])
        Log.e("ResponseRitro", response.title)
    }

    private fun initPager(coulumnOne:String, coulumnTwo:String, coulumnThree:String)
    {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PizzaFragmentFirst(), coulumnOne)
        adapter.addFragment(SushiFragment(), coulumnTwo)
        adapter.addFragment(PizzaFragmentFirst(), coulumnThree)
        viewPager.adapter = adapter
    }

    private class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(
        supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()


        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }




}




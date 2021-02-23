/*
 * Copyright (c) 2021.year.
 * this project made by Hamiedreza Marzbani for DinDinn.
 * contact :
 * mail : hamied.reza.m@gmail.com
 * phone :+989185584088
 */

package com.example.dindinnassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cart.*

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mBackBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
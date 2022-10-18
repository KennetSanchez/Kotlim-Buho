package com.example.buho

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("ViewConstructor")
class MyEventCardComponent (
    val title: String,
    val state: String,
    val classroom: String,
    val schedule: String,
    val description: String,
    context: Context,
) {}
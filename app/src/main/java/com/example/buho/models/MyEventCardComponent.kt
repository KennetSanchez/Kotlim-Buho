package com.example.buho.models

import java.io.Serializable

data class MyEventCardComponent (
    var title : String = "",
    var date: String = "",
    var classroom: String = "",
    var schedule: String = "",
    var description: String = ""
): Serializable {}
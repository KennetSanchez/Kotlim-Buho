package com.example.buho.models

import java.io.Serializable

data class SuggestedEventComponent (
    var title : String = "",
    var date: String = "",
    var description: String = "",
    var classroom: String = "",
    var schedule: String = "",
    var professor: String = ""
): Serializable {}
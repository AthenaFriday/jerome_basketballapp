package com.android.basketballapp.data


data class Team(
    val id: Int,
    val name: String,
    val fullName: String,
    val city: String,
    val conference: String,
    val division: String,
    val abbreviation: String
)
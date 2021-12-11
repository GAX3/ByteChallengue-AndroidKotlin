package com.example.bytechallengue.api

data class MyDataItem(
    val batters: Batters,
    val id: String,
    val name: String,
    val ppu: Double,
    val topping: List<Topping>,
    val type: String
)
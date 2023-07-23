package com.example.hokkey_helper.model

data class MatchData(
    val id:String,
    val commands: Pair<Command,Command>,
    val countries: Pair<Country,Country>,
    val name:String,
    val isBlocked: Boolean = false,
    val priceList: List<SaleData>
)

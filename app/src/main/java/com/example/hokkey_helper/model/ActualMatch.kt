package com.example.hokkey_helper.model

data class ActualMatch(
    val id:String,
    var scope: Pair<Int,Int>,
    val commands:Pair<String,String>
)

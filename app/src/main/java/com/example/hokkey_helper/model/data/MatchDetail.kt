package com.example.hokkey_helper.model.data

import android.app.Person

data class MatchDetail(
    val matchId: String,
    val city: String,
    val data: String,
    val commandList: Pair<List<Pair<String, String>>, List<Pair<String, String>>>,
    val treners: Pair<String,String>,
    val score: Pair<Int,Int>
)

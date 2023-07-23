package com.example.hokkey_helper.model.data

data class MatchDetail(
    val matchId: String,
    val commandList: Pair<List<String>, List<String>>,
    val treners: Pair<String,String>
)

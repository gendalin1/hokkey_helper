package com.example.hokkey_helper.model

data class Country(
    val id:String,
    val name:String,
    val imageURl: String,
    val isBlock: Boolean = false,
    val price: Int? = null
)

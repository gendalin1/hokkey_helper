package com.example.hokkey_helper.model

data class Command(
    val id: String,
    val name: String,
    val country: String,
    val imageURL: String,
    val isBlock:Boolean = false,
    val price: Int? = null
)

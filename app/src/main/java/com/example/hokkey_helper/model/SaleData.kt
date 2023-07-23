package com.example.hokkey_helper.model

import com.example.hokkey_helper.model.enum.SaleType

data class SaleData(
    val id: String,
    val type:SaleType,
    val name: String,
    val price: Int
)

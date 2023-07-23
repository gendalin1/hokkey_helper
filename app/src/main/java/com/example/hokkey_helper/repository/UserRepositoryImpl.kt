package com.example.hokkey_helper.repository

import com.example.hokkey_helper.repository.interfaces.UserReporitory

class UserRepositoryImpl():UserReporitory {
    private val userPermission: MutableMap<String,ArrayList<String>> = mutableMapOf( "1" to ArrayList())



    override fun getUserPermission(userId: String): List<String> {
        return userPermission[userId]?.toList() ?: listOf()
    }

    override fun addPermission(userId: String, id: String) {
        userPermission[userId]?.add(id)
    }
}
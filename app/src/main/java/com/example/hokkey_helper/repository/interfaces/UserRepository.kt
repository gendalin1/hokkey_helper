package com.example.hokkey_helper.repository.interfaces


interface UserRepository {
    fun getUserPermission(userId: String):List<String>
    fun addPermission(userId: String, id: String)
}
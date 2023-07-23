package com.example.hokkey_helper.repository.interfaces

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match



interface UserReporitory {
    fun getUserPermission(userId: String):List<String>
    fun addPermission(userId: String, id: String)
}
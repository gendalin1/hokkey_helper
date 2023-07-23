package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.repository.interfaces.UserRepository
import javax.inject.Inject

class GetUserPermissionUseCase @Inject constructor(val userReporitory: UserRepository) {
    fun execute(id: String):List<String>{
        return userReporitory.getUserPermission(id)
    }
}
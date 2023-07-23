package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.repository.interfaces.UserRepository
import javax.inject.Inject

class AddPermissionToUserUseCase @Inject constructor(val userRepository: UserRepository) {
    fun execute(userId: String, id:String){
        userRepository.addPermission(userId,id)
    }
}
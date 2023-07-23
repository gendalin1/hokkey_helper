package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import com.example.hokkey_helper.repository.interfaces.UserReporitory
import javax.inject.Inject

class GetUserPermissionUseCase @Inject constructor(val userReporitory: UserReporitory) {
    fun execute(id: String):List<String>{
        return userReporitory.getUserPermission(id)
    }
}
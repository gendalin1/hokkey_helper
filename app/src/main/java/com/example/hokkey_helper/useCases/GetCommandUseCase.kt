package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import javax.inject.Inject

class GetCommandUseCase @Inject constructor(val matchesRepository: MatchesRepository) {
    fun execute():List<Command>{
        return matchesRepository.getCommand()
    }
}
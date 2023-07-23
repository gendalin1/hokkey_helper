package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(val matchesRepository: MatchesRepository) {
    fun execute():List<Match>{
        return matchesRepository.getMatches()
    }
}
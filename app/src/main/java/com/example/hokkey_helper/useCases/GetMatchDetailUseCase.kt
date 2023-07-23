package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.data.MatchDetail
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import javax.inject.Inject

class GetMatchDetailUseCase @Inject constructor(val matchesRepository: MatchesRepository) {
    fun execute(id: String):Pair<Match, MatchDetail>{
        return matchesRepository.getMatchDetail(id)
    }
}
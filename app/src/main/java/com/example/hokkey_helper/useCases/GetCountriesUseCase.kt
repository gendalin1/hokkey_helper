package com.example.hokkey_helper.useCases

import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(val matchesRepository: MatchesRepository) {
    fun execute():List<Country>{
        return matchesRepository.getCountries()
    }
}
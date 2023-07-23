package com.example.hokkey_helper.repository.interfaces

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match

interface MatchesRepository {
    fun getCountry():List<Country>
    fun getCommand():List<Command>
    fun getMatches():List<Match>
}
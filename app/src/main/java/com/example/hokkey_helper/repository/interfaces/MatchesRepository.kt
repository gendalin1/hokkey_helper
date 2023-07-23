package com.example.hokkey_helper.repository.interfaces

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.Sportsmen
import com.example.hokkey_helper.model.data.MatchDetail

interface MatchesRepository {
    fun getCountries():List<Country>
    fun getCommands():List<Command>
    fun getMatches():List<Match>
    fun getSportsmen(id:String):Sportsmen
    fun getMatchDetail(id:String): Pair<Match,MatchDetail>
    fun getCommand(id:String):Command
    fun getCountry(id:String):Country
}
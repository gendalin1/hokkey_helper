package com.example.hokkey_helper.repository

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.repository.interfaces.MatchesRepository


class MatchesRepositoryImpl(): MatchesRepository {
    val countryList = listOf<Country>(
        Country(id = "1","Китай", ""),
        Country(id = "2","КНДР","",true, 30),
        Country(id = "3", "Россия","" )
    )

    val commandList = listOf<Command>(
        Command("11","Команда1", "1", ""),
        Command("12","ТяньАньМень", "1", "",true, 15),
        Command("21", "Ким","2",""),
        Command("22", "Чен","2",""),
        Command("23","Ын","2","",true, 10),
        Command("31","Команда2","3",""),
        Command("32","Команда3","3","")
    )

    val matchList = listOf(
        Match("111",("11" to "21"), "Матч-1", (5 to 3) ),
        Match("121",("12" to "31"), "Матч-2", (1 to 4) ),
        Match("112",("32" to "11"), "Матч-3", (3 to 3) )
    )

    override fun getCountry(): List<Country> {
        return countryList
    }

    override fun getCommand(): List<Command> {
        return commandList
    }

    override fun getMatches(): List<Match> {
        return matchList
    }
}
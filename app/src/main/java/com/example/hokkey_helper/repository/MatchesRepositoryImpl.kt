package com.example.hokkey_helper.repository

import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.Sportsmen
import com.example.hokkey_helper.model.data.MatchDetail
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
        Match("11111",("11" to "21"), "Матч-1" ),
        Match("12111",("12" to "31"), "Матч-2" ),
        Match("11511",("32" to "11"), "Матч-3" )
    )

    val matchDetail = listOf(
        MatchDetail("11111",
            "Город1",
            "дата1",
            (listOf(
            "111" to "Вратарь",
            "112" to "Левый Защитник",
            "113" to "Правый Защитник",
            "114" to "Левый Нападающий",
            "115" to "Центральный Нападающий",
            "116" to "Правый Нападающий"
        ) to listOf(
            "211" to "Вратарь",
            "212" to "Левый Защитник",
            "213" to "Правый Защитник",
            "214" to "Левый Нападающий",
            "215" to "Центральный Нападающий",
            "216" to "Правый Нападающий"
        ) ), treners = ("Тренер 1" to "Тренер2"),
            (5 to 3)),
        MatchDetail("12111",
            "Город2",
            "дата2",
            (listOf(
            "121" to "Вратарь",
            "122" to "Левый Защитник",
            "123" to "Правый Защитник",
            "124" to "Левый Нападающий",
            "125" to "Центральный Нападающий",
            "126" to "Правый Нападающий"
        ) to listOf(
            "311" to "Вратарь",
            "312" to "Левый Защитник",
            "313" to "Правый Защитник",
            "314" to "Левый Нападающий",
            "315" to "Центральный Нападающий",
            "316" to "Правый Нападающий"
        )), ("Тренер 3" to "Тренер4"),
            (1 to 4)),
        MatchDetail("11511",
            "Город3",
            "дата3",
            (listOf(
            "321" to "Вратарь",
            "322" to "Левый Защитник",
            "323" to "Правый Защитник",
            "324" to "Левый Нападающий",
            "325" to "Центральный Нападающий",
            "326" to "Правый Нападающий"
        ) to listOf(
            "111" to "Вратарь",
            "112" to "Левый Защитник",
            "113" to "Правый Защитник",
            "114" to "Левый Нападающий",
            "115" to "Центральный Нападающий",
            "116" to "Правый Нападающий"
        )), ("Тренер 5" to "Тренер1"),
            (2 to 3))
        )

    val sportsmen = listOf(
        Sportsmen("111","Спортсмен1"),
        Sportsmen("112","Спортсмен2"),
        Sportsmen("113","Спортсмен3"),
        Sportsmen("114","Спортсмен4"),
        Sportsmen("115","Спортсмен5"),
        Sportsmen("116","Спортсмен6"),

        Sportsmen("121","Спортсмен7"),
        Sportsmen("122","Спортсмен8"),
        Sportsmen("123","Спортсмен9"),
        Sportsmen("124","Спортсмен10"),
        Sportsmen("125","Спортсмен11"),
        Sportsmen("126","Спортсмен12"),

        Sportsmen("211","Спортсмен13"),
        Sportsmen("212","Спортсмен14"),
        Sportsmen("213","Спортсмен15"),
        Sportsmen("214","Спортсмен16"),
        Sportsmen("215","Спортсмен17"),
        Sportsmen("216","Спортсмен18"),

        Sportsmen("221","Спортсмен19"),
        Sportsmen("222","Спортсмен20"),
        Sportsmen("223","Спортсмен21"),
        Sportsmen("224","Спортсмен22"),
        Sportsmen("225","Спортсмен23"),
        Sportsmen("226","Спортсмен24"),

        Sportsmen("231","Спортсмен25"),
        Sportsmen("232","Спортсмен26"),
        Sportsmen("233","Спортсмен27"),
        Sportsmen("234","Спортсмен28"),
        Sportsmen("235","Спортсмен29"),
        Sportsmen("236","Спортсмен30"),

        Sportsmen("311","Спортсмен31"),
        Sportsmen("312","Спортсмен32"),
        Sportsmen("313","Спортсмен33"),
        Sportsmen("314","Спортсмен34"),
        Sportsmen("315","Спортсмен35"),
        Sportsmen("316","Спортсмен36"),

        Sportsmen("321","Спортсмен37"),
        Sportsmen("322","Спортсмен38"),
        Sportsmen("323","Спортсмен39"),
        Sportsmen("324","Спортсмен40"),
        Sportsmen("325","Спортсмен41"),
        Sportsmen("326","Спортсмен42")
    )



    override fun getCountries(): List<Country> {
        return countryList
    }

    override fun getCommands(): List<Command> {
        return commandList
    }

    override fun getMatches(): List<Match> {
        return matchList
    }
    override fun getSportsmen(id: String): Sportsmen {
        return sportsmen.first{it.id == id}
    }

    override fun getCountry(id: String): Country {
        return countryList.first { it.id == id }
    }

    override fun getCommand(id: String): Command {
        return commandList.first{ it.id == id}
    }


    override fun getMatchDetail(id: String): Pair<Match, MatchDetail> {
        val match = matchList.first { it.id == id }
        val matchDetail = matchDetail.first { it.matchId == id }
        return match to matchDetail
    }

}
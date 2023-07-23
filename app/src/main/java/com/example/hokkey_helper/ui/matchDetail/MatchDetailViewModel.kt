package com.example.hokkey_helper.ui.matchDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hokkey_helper.MainApp
import com.example.hokkey_helper.di.UserManager
import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.Sportsmen
import com.example.hokkey_helper.model.data.MatchDetail
import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import com.example.hokkey_helper.useCases.GetMatchDetailUseCase
import com.example.hokkey_helper.useCases.GetSportsmenUseCase
import javax.inject.Inject

class MatchDetailViewModel : ViewModel() {

    @Inject
    lateinit var getSportsmenUseCase: GetSportsmenUseCase

    @Inject
    lateinit var getMatchDetailUseCase: GetMatchDetailUseCase

    @Inject
    lateinit var matchRepository: MatchesRepository

    @Inject
    lateinit var userManager: UserManager


    private val _match = MutableLiveData<Match>()
    val _matchDetail = MutableLiveData<MatchDetail>()
    private val _firstSportsmens = MutableLiveData<List<Pair<Sportsmen, String>>>()
    val firstSportsmens: LiveData<List<Pair<Sportsmen, String>>> = _firstSportsmens

    private val _secondSportsmens = MutableLiveData<List<Pair<Sportsmen, String>>>()
    val secondSportsmens: LiveData<List<Pair<Sportsmen, String>>> = _secondSportsmens

    val _commands = MutableLiveData<Pair<Command, Command>>()
    val _countries = MutableLiveData<Pair<Country, Country>>()

    var id: String = ""
    init {
        MainApp.get().appComponent.inject(this)
    }
    fun initData(){
        getMatchDetailUseCase.execute(id).first.let{
            _match.value = it
        }
        getMatchDetailUseCase.execute(id).second.let{
            _matchDetail.value = it
        }

        _commands.value = (matchRepository.getCommand(_match.value?.commands?.first ?: "")
                to matchRepository.getCommand(_match.value?.commands?.second ?: ""))

        _countries.value = (matchRepository.getCountry(_commands.value?.first?.country ?: "")
                to matchRepository.getCountry(_commands.value?.second?.country ?: ""))

        _matchDetail.value?.commandList?.let{
            _firstSportsmens.value = it.first.map{item -> (getSportsmenUseCase.execute(item.first) to item.second) }
            _secondSportsmens.value = it.second.map{item -> (getSportsmenUseCase.execute(item.first) to item.second) }

        }
    }
}
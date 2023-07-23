package com.example.hokkey_helper.ui.matchDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hokkey_helper.MainApp
import com.example.hokkey_helper.di.UserManager
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.Sportsmen
import com.example.hokkey_helper.model.data.MatchDetail
import com.example.hokkey_helper.useCases.AddPermissionToUserUseCase
import com.example.hokkey_helper.useCases.GetCommandUseCase
import com.example.hokkey_helper.useCases.GetCountryUseCase
import com.example.hokkey_helper.useCases.GetMatchDetailUseCase
import com.example.hokkey_helper.useCases.GetMatchesUseCase
import com.example.hokkey_helper.useCases.GetSportsmenUseCase
import com.example.hokkey_helper.useCases.GetUserPermissionUseCase
import javax.inject.Inject

class MatchDetailViewModel : ViewModel() {

    @Inject
    lateinit var getSportsmenUseCase: GetSportsmenUseCase

    @Inject
    lateinit var getMatchDetailUseCase: GetMatchDetailUseCase

    @Inject
    lateinit var userManager: UserManager


    private val _match = MutableLiveData<Match?>()
    private val _matchDetail = MutableLiveData<MatchDetail?>()
    private val _firstSportsmens = MutableLiveData<List<Pair<Sportsmen, String>>>()
    val firstSportsmens: LiveData<List<Pair<Sportsmen, String>>> = _firstSportsmens

    private val _secondSportsmens = MutableLiveData<List<Pair<Sportsmen, String>>>()
    val secondSportsmens: LiveData<List<Pair<Sportsmen, String>>> = _secondSportsmens

    var id: String = ""
    init {
        MainApp.get().appComponent.inject(this)
    }
    fun initData(){
        _match.value = getMatchDetailUseCase.execute(id).first
        _matchDetail.value = getMatchDetailUseCase.execute(id).second
        _matchDetail.value?.commandList?.let{
            _firstSportsmens.value = it.first.map{item -> (getSportsmenUseCase.execute(item.first) to item.second) }
            _secondSportsmens.value = it.second.map{item -> (getSportsmenUseCase.execute(item.first) to item.second) }

        }
    }
}
package com.example.hokkey_helper.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.hokkey_helper.MainApp
import com.example.hokkey_helper.di.UserManager
import com.example.hokkey_helper.model.Command
import com.example.hokkey_helper.model.Country
import com.example.hokkey_helper.model.Match
import com.example.hokkey_helper.model.MatchData
import com.example.hokkey_helper.model.SaleData
import com.example.hokkey_helper.model.enum.SaleType
import com.example.hokkey_helper.useCases.AddPermissionToUserUseCase
import com.example.hokkey_helper.useCases.GetCommandsUseCase
import com.example.hokkey_helper.useCases.GetCountriesUseCase
import com.example.hokkey_helper.useCases.GetMatchesUseCase
import com.example.hokkey_helper.useCases.GetUserPermissionUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class MatchesViewModel : ViewModel() {

    @Inject
    lateinit var getCountryUseCase: GetCountriesUseCase

    @Inject
    lateinit var getCommandUseCase: GetCommandsUseCase

    @Inject
    lateinit var getMatchesUseCase: GetMatchesUseCase

    @Inject
    lateinit var getUserPermissionUseCase: GetUserPermissionUseCase

    @Inject
    lateinit var addPermissionToUserUseCase: AddPermissionToUserUseCase

    @Inject
    lateinit var userManager: UserManager


    init {
        MainApp.get().appComponent.inject(this)
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _countryData = MutableLiveData<List<Country>>()
    private val _commandData = MutableLiveData<List<Command>>()
    private val _matchesData = MutableLiveData<List<Match>>()
    private val _userPermission = MutableLiveData<List<String>>()

    private val _matchesDataResult = MutableLiveData<List<MatchData>>()
    val matchesDataResult: LiveData<List<MatchData>> = _matchesDataResult

    fun addPermissions(saleList: List<SaleData>){
        saleList.forEach {
            addPermissionToUserUseCase.execute(userManager.userId, it.id)
        }
        _userPermission.value = getUserPermissionUseCase.execute(userManager.userId)
    }

    fun initData(){
        _countryData.value = getCountryUseCase.execute()
        _commandData.value = getCommandUseCase.execute()
        _matchesData.value = getMatchesUseCase.execute()
        _userPermission.value = getUserPermissionUseCase.execute(userManager.userId)

        combine(
            _countryData.asFlow(),
            _commandData.asFlow(),
            _matchesData.asFlow(),
            _userPermission.asFlow()
        ){ counries, commands, matches, permission ->
            val result = ArrayList<MatchData>()
            matches.forEach{match ->
                val pairCommands  = (commands.first{ it.id == match.commands.first} to commands.first{it.id == match.commands.second})
                val pairCountry = (counries.first{it.id == pairCommands.first.country} to counries.first{it.id == pairCommands.second.country})
                var isBlocked = false
                val priceList = ArrayList<SaleData>()
                if (pairCountry.first.isBlock && !permission.contains(pairCountry.first.id)){
                    isBlocked = true
                    priceList.add(
                        SaleData(
                            pairCountry.first.id,
                            SaleType.COUNTRY,
                            pairCountry.first.name,
                            pairCountry.first.price ?: 0)) }
                if (pairCountry.first != pairCountry.second && pairCountry.second.isBlock
                    && !permission.contains(pairCountry.second.id)){
                    isBlocked = true
                    priceList.add(
                        SaleData(
                            pairCountry.second.id,
                            SaleType.COUNTRY,
                            pairCountry.second.name,
                            pairCountry.second.price ?: 0)) }


                if (pairCommands.first.isBlock && !permission.contains(pairCommands.first.id)){
                    isBlocked = true
                    priceList.add(
                        SaleData(
                            pairCommands.first.id,
                            SaleType.COMMAND,
                            pairCommands.first.name,
                            pairCommands.first.price ?: 0)) }

                if (pairCommands.second.isBlock && !permission.contains(pairCommands.second.id)){
                    isBlocked = true
                    priceList.add(
                        SaleData(
                            pairCommands.second.id,
                            SaleType.COMMAND,
                            pairCommands.second.name,
                            pairCommands.second.price ?: 0)) }

                result.add(
                    MatchData(
                        id = match.id,
                        commands = pairCommands,
                        countries = pairCountry,
                        name = match.name,
                        isBlocked = isBlocked,
                        priceList = priceList.toList()
                    )
                )
            }
            _matchesDataResult.value = result.toList()
        }.launchIn(viewModelScope)

    }

}
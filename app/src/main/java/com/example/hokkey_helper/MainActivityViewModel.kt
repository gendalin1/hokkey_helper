package com.example.hokkey_helper

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hokkey_helper.di.UserManager
import com.example.hokkey_helper.model.ActualMatch
import com.example.hokkey_helper.model.Sportsmen
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
    @Inject
    lateinit var userManager: UserManager
    init {
        MainApp.get().appComponent.inject(this)
    }

    var goToShop:() -> Unit ={}


    val time = MutableLiveData<Long>(0)
    val actualMatch = MutableLiveData<ActualMatch>(ActualMatch("111144",0 to 0, "" to ""))
}
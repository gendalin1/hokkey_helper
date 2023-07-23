package com.example.hokkey_helper

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hokkey_helper.di.UserManager
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
    @Inject
    lateinit var userManager: UserManager
    init {
        MainApp.get().appComponent.inject(this)
    }

    var goToShop:() -> Unit ={}
}
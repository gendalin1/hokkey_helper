package com.example.hokkey_helper.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hokkey_helper.MainApp
import com.example.hokkey_helper.di.UserManager
import javax.inject.Inject

class StoreViewModel : ViewModel() {

    @Inject
    lateinit var userManager: UserManager

    init {
        MainApp.get().appComponent.inject(this)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is store Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.hokkey_helper.di

import com.orhanobut.hawk.Hawk
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    var userId: String = "111111111111111"
    var coins = BehaviorSubject.createDefault(100)

    init {
        if (Hawk.contains("coins")){
            coins.onNext(Hawk.get("coins", 100))
        }
    }
}
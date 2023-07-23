package com.example.hokkey_helper.di

import android.app.Application
import com.example.hokkey_helper.ui.assistant.AssistantViewModel
import com.example.hokkey_helper.ui.matches.MatchesViewModel
import com.example.hokkey_helper.ui.rule.RuleViewModel
import com.example.hokkey_helper.ui.store.StoreViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    fun inject(viewModel: MatchesViewModel)
    fun inject(viewModel: RuleViewModel)
    fun inject(viewModel: AssistantViewModel)
    fun inject(viewModel: StoreViewModel)

}

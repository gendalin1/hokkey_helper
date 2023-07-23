package com.example.hokkey_helper

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hokkey_helper.databinding.ActivityMainBinding
import com.example.hokkey_helper.service.TimerService
import com.orhanobut.hawk.Hawk
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity(), TimerService.TimerUpdateListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var timerService: TimerService
    private var isServiceBound = false
    private val viewModel: MainActivityViewModel by viewModels()
    var disposable: Disposable? = null

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("AAA","aboba1")
            val binder = service as TimerService.LocalBinder
            timerService = binder.getService()
            timerService.registerTimerUpdateListener(this@MainActivity)
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_matches, R.id.navigation_rule, R.id.navigation_assistant, R.id.navigation_store
            )
        )

        disposable = viewModel.userManager.coins.subscribe{
            Hawk.put("coins",it)
            binding.coinCount.text = it.toString()
        }

        val serviceIntent = Intent(this, TimerService::class.java)
        startService(serviceIntent)



        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.add.setOnClickListener {
            viewModel.userManager.coins.onNext(100)
            navView.selectedItemId = R.id.navigation_store
        }
        viewModel.goToShop = {
            navView.selectedItemId = R.id.navigation_store
        }
    }

    override fun onTimerUpdate(currentTime: Long) {
        viewModel.time.value = currentTime
    }
    override fun onStart() {
        super.onStart()
        Log.e("AAA","aboba2")
        bindService(Intent(this, TimerService::class.java),serviceConnection,  Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }


    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
package com.example.hokkey_helper.ui.store

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.hokkey_helper.databinding.FragmentStoreBinding
import com.orhanobut.hawk.Hawk

class StoreFragment : Fragment() {

    lateinit var binding: FragmentStoreBinding
    private lateinit var shakeDetector: ShakeDetector

    private val viewModel: StoreViewModel by viewModels()

    // This property is only valid between onCreateView and

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        val sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector(requireContext(),object : ShakeDetector.OnShakeListener{
            override fun onShake() {
                viewModel.userManager.coins.onNext(viewModel.userManager.coins.value?.plus(1) ?: 100)
            }
        })

        sensorManager.registerListener(
            shakeDetector,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
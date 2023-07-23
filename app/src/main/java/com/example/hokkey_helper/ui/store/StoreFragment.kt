package com.example.hokkey_helper.ui.store

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.hokkey_helper.R
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
                startAnimation()
            }
        })

        //startFlipAnimation()

        sensorManager.registerListener(
            shakeDetector,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        return binding.root
    }


    private fun startAnimation() {
        val screenWidth = resources.displayMetrics.widthPixels
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 500
        rotateAnimation.repeatCount = 2

        val moveRightAnimation = TranslateAnimation(0f, screenWidth.toFloat()*3/4, 0f, 0f)
        moveRightAnimation.duration = 1500

        val flipAnimation = AnimationSet(true)
        flipAnimation.addAnimation(rotateAnimation)
        flipAnimation.addAnimation(moveRightAnimation)



        flipAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                val moveLeftAnimation = TranslateAnimation(
                    -(screenWidth*3/4).toFloat(),
                    0f,
                    0f,
                    0f
                )
                moveLeftAnimation.duration = 1500
                val flipAnimation2 = AnimationSet(true)
                flipAnimation2.addAnimation(rotateAnimation)
                flipAnimation2.addAnimation(moveLeftAnimation)
                flipAnimation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        // По окончании анимации движения влево и возврата в центр
                        // запускаем анимацию заново
                        viewModel.userManager.coins.onNext(viewModel.userManager.coins.value?.plus(1) ?: 100)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })
                binding.coinIcon.startAnimation(flipAnimation2)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.coinIcon.startAnimation(flipAnimation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
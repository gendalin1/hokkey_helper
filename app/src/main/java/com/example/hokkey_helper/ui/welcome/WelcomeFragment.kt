package com.example.hokkey_helper.ui.welcome
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hokkey_helper.R
import com.example.hokkey_helper.databinding.FragmentWelcomeScreenBinding

class WelcomeFragment : DialogFragment() {

    private var isDraw: Boolean = false
    lateinit var binding: FragmentWelcomeScreenBinding
    private val fragmentList = ArrayList<Fragment>()
    val pages = listOf(
        R.id.navigation_matches,
        R.id.navigation_rule,
        R.id.navigation_assistant,
        R.id.navigation_store
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeScreenBinding.inflate(inflater)

        val wight = resources.configuration.screenWidthDp / pages.size
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = IntroSliderAdapter(requireActivity())
        binding.vpIntroSlider.adapter = adapter

        for (page in pages)
            fragmentList.add(FragmentItem.newInstance(page))
        fragmentList.add(FragmentItem.newInstance(0))

        adapter.setFragmentList(fragmentList)

        binding.indicatorLayout.setIndicatorCount(adapter.itemCount - 1)
        binding.indicatorLayout.selectCurrentPosition(0)
        registerListeners()
    }

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    private fun setWindowParams() {
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun registerListeners() {
        binding.vpIntroSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == fragmentList.size - 1)
                    dismiss()
                binding.indicatorLayout.selectCurrentPosition(position)
            }
        })
        binding.buttonSkip.setOnClickListener {
            dismiss()
        }
    }


    companion object {

        fun newInstance(): WelcomeFragment {
            val fragment = WelcomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun dismiss() {

        super.dismiss()
        requireActivity().supportFragmentManager.setFragmentResult("WelcomeFragment", bundleOf())
    }
}
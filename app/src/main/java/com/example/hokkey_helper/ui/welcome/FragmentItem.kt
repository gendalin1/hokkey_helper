package com.example.hokkey_helper.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hokkey_helper.R
import com.example.hokkey_helper.databinding.FragmentWelcomeItemBinding

class FragmentItem : Fragment() {
    lateinit var binding: FragmentWelcomeItemBinding
    var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeItemBinding.inflate(inflater)
        page = arguments?.getInt("page") ?: 1
        when (page) {
            R.id.navigation_matches -> {
                initView(
                    "Матчи",
                    "На экране Матчи вы можете просматривать информацию о прошедших матчах."
                    //R.drawable.ic_page_tutorial_region
                )
            }
            R.id.navigation_rule -> {
                initView(
                    "Правила",
                    "На экране правила вы можете ознакомится с основными правилами хоккея," +
                            "а также узнать интересную информацию, связанную с этим видом спорта."
                )
            }
            R.id.navigation_assistant -> {

                initView(
                    "Ассистент",
                    "Ассистент поможет вам следить за текущей игрой, отслеживать счёт и так далее."
                )
            }
            R.id.navigation_store -> {
                initView(
                    "Магазин",
                    "В разделе магазинчик вы можете пополнить свой запас монеток. Нужно всего лишь потрясти телефон и монетка у вас в кармане."
                )
            }
            0 -> {
                initView(
                    "",
                    "",
                )
            }
            else -> {
                initView(
                    "",
                    ""
                )
            }
        }
        return binding.root
    }

    private fun initView(title: String, content: String) {
        binding.content.text = content
        binding.page.text = title
    }

    companion object {

        fun newInstance(page: Int): FragmentItem {
            val fragment = FragmentItem()
            val args = Bundle()

            args.putInt("page", page)

            fragment.arguments = args
            return fragment
        }
    }
}
package com.example.hokkey_helper.ui.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hokkey_helper.databinding.FragmentRulesBinding

class RuleFragment : Fragment() {

    lateinit var binding: FragmentRulesBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRulesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.preview1.setOnClickListener {
            val rotation = if(!binding.fullInfo1.isVisible)
                90f else 0f
            binding.showMore1.animate().rotation(
                rotation
            ).withEndAction {
                binding.fullInfo1.isVisible = !binding.fullInfo1.isVisible
            }.duration = 500
            binding.showMore1.animate()
        }
        binding.preview2.setOnClickListener {
            val rotation = if(!binding.fullInfo2.isVisible)
                90f else 0f
            binding.showMore2.animate().rotation(
                rotation
            ).withEndAction {
                binding.fullInfo2.isVisible = !binding.fullInfo2.isVisible
            }.duration = 500
            binding.showMore2.animate()
        }
        binding.preview3.setOnClickListener {
            val rotation = if(!binding.fullInfo3.isVisible)
                90f else 0f
            binding.showMore3.animate().rotation(
                rotation
            ).withEndAction {
                binding.fullInfo3.isVisible = !binding.fullInfo3.isVisible
            }.duration = 500
            binding.showMore3.animate()
        }
        binding.preview4.setOnClickListener {
            val rotation = if(!binding.fullInfo4.isVisible)
                90f else 0f
            binding.showMore4.animate().rotation(
                rotation
            ).withEndAction {
                binding.fullInfo4.isVisible = !binding.fullInfo4.isVisible
            }.duration = 500
            binding.showMore4.animate()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
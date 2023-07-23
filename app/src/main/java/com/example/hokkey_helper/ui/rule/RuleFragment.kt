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
            binding.fullInfo1.isVisible = !binding.fullInfo1.isVisible
        }
        binding.preview2.setOnClickListener {
            binding.fullInfo2.isVisible = !binding.fullInfo2.isVisible
        }
        binding.preview3.setOnClickListener {
            binding.fullInfo3.isVisible = !binding.fullInfo3.isVisible
        }
        binding.preview4.setOnClickListener {
            binding.fullInfo4.isVisible = !binding.fullInfo4.isVisible
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
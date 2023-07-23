package com.example.hokkey_helper.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hokkey_helper.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    lateinit var binding: FragmentStoreBinding

    // This property is only valid between onCreateView and

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(StoreViewModel::class.java)

        binding = FragmentStoreBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textHome
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
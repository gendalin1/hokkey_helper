package com.example.hokkey_helper.ui.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hokkey_helper.MainApp
import com.example.hokkey_helper.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {

    lateinit var binding: FragmentMatchesBinding

    private val viewModel: MatchesViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        val adapter = MatchesAdapter()
        binding.recycler.adapter = adapter
        viewModel.initData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.matchesDataResult.observe(viewLifecycleOwner){
            Log.e("AAA","${it.size}")
            (binding.recycler.adapter as MatchesAdapter).refresh(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
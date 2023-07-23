package com.example.hokkey_helper.ui.matchDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hokkey_helper.databinding.FragmentMatchDetailBinding
import com.example.hokkey_helper.ui.matches.MatchesViewModel

class MatchDetailFragment : Fragment() {

    lateinit var binding: FragmentMatchDetailBinding
    lateinit var firstAdapter:SportsmensAdapter
    lateinit var secondAdapter:SportsmensAdapter

    private val matchId: String by lazy(LazyThreadSafetyMode.NONE) {
        (requireArguments().getString("item_id") ?: "")
    }

    private val viewModel: MatchDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        viewModel.id = matchId
        firstAdapter = SportsmensAdapter()
        secondAdapter = SportsmensAdapter()
        binding.recycler1.adapter = firstAdapter
        binding.recycler2.adapter = secondAdapter
        viewModel.initData()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.firstSportsmens.observe(viewLifecycleOwner){
            firstAdapter.refresh(it)
        }
        viewModel.secondSportsmens.observe(viewLifecycleOwner){
            secondAdapter.refresh(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }


}
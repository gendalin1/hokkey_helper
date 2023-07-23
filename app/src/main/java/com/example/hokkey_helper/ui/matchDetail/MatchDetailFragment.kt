package com.example.hokkey_helper.ui.matchDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.hokkey_helper.R
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

        viewModel._matchDetail.observe(viewLifecycleOwner){
            binding.city.text = binding.city.text.toString() + it.city
            binding.fieldTrainer1.text = it.treners.first
            binding.fieldTrainer2.text = it.treners.second
            binding.count1.text = it.score.first.toString()
            binding.count2.text = it.score.second.toString()
        }

        viewModel._countries.observe(viewLifecycleOwner){
            binding.fieldCountry1.text = it.first.name
            binding.fieldCountry2.text = it.second.name
        }

        viewModel._commands.observe(viewLifecycleOwner){
            binding.fieldName1.text = it.first.name
            binding.fieldName2.text = it.second.name
            Glide.with(requireContext())
                .load(it.first.imageURL)
                .fitCenter()
                .placeholder(R.drawable.no_img)
                .error(R.drawable.no_img)
                .into(binding.firstImage)

            Glide.with(requireContext())
                .load(it.second.imageURL)
                .fitCenter()
                .placeholder(R.drawable.no_img)
                .error(R.drawable.no_img)
                .into(binding.secondImage)
        }

        binding.preview1.setOnClickListener {
            binding.fullInfo1.isVisible = !binding.fullInfo1.isVisible
        }
        binding.preview2.setOnClickListener {
            binding.fullInfo2.isVisible = !binding.fullInfo2.isVisible
        }

        super.onViewCreated(view, savedInstanceState)
    }


}
package com.example.hokkey_helper.ui.assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.hokkey_helper.MainActivityViewModel
import com.example.hokkey_helper.databinding.FragmentAssistantBinding

class AssistantFragment : Fragment() {

    lateinit var binding: FragmentAssistantBinding

    private val viewModel:MainActivityViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(AssistantViewModel::class.java)

        binding = FragmentAssistantBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.time.observe(viewLifecycleOwner){
            val seconds = (it%60).toString()
            val minutes = (it/60).toString()

            binding.timerSecond.text = if (seconds.length == 1) "0$seconds"
            else seconds

            binding.timerMinute.text = if (minutes.length == 1) "0$minutes"
            else minutes

        }
        viewModel.actualMatch.observe(viewLifecycleOwner){
            binding.count1.text = it.scope.first.toString()
            binding.count2.text = it.scope.second.toString()
        }

        binding.buttonTeam1Score.setOnClickListener {
            viewModel.actualMatch.value?.let {
                it.scope = it.scope.first.plus(1) to it.scope.second
                binding.count1.text = it.scope.first.toString()
            }

        }

        binding.buttonTeam2Score.setOnClickListener {
            viewModel.actualMatch.value?.let {
                it.scope = it.scope.first to it.scope.second.plus(1)
                binding.count2.text = it.scope.second.toString()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
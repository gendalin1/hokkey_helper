package com.example.hokkey_helper.ui.matches

import android.app.Dialog
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.hokkey_helper.MainActivityViewModel
import com.example.hokkey_helper.R
import com.example.hokkey_helper.databinding.BuyMatchDialogFragmentBinding
import com.example.hokkey_helper.databinding.FragmentMatchesBinding
import com.example.hokkey_helper.model.MatchData
import com.example.hokkey_helper.model.enum.SaleType

class BuyMatchDialogFragment: DialogFragment() {

    private lateinit var binding: BuyMatchDialogFragmentBinding
    val mainViewModel: MainActivityViewModel by activityViewModels()
    var callback: () -> Unit = {}
    var matchData:MatchData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BuyMatchDialogFragmentBinding.inflate(inflater, container, false)
        var text = "Для доступа к матчу ${matchData?.name} требуется докупить:"
        val countries = matchData?.priceList?.filter { it.type == SaleType.COUNTRY }
        val commands = matchData?.priceList?.filter { it.type == SaleType.COMMAND }

        countries?.let{ list ->
            if(list.isNotEmpty()) {
                text += " Страны: "
                list.withIndex().forEach {
                    text += it.value.name
                    text += if (it.index != countries.size - 1)
                        ", "
                    else
                        "."
                }
            }
        }
        commands?.let{list->
            if(list.isNotEmpty()) {
                text += " Команды: "
                list.withIndex().forEach {
                    text += it.value.name
                    text += if (it.index != commands.size - 1)
                        ", "
                    else
                        "."
                }
            }
        }
        binding.text.text = text



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sum = (matchData?.priceList?.sumOf { it.price } ?: 0)

        if ((mainViewModel.userManager.coins.value?: 0)< sum ){
            binding.buySomething.visibility = View.GONE
        }
        else{
            binding.buySomething.visibility = View.VISIBLE
            binding.buySomething.text = "Купить за $sum"
            binding.buySomething.setOnClickListener{
                mainViewModel.userManager.coins.onNext(mainViewModel.userManager.coins.value?.minus(sum) ?: 100)
                callback()
                dismiss()
            }
        }
        binding.goToShop.setOnClickListener{
            dismiss()
            mainViewModel.goToShop()
        }

        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
        /*val inset = InsetDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                (R.drawable.background_dialog)
            ), (16 * resources.displayMetrics.density).toInt()
        )*/
        //dialog?.window?.setBackgroundDrawable(inset)
    }

    companion object {

        const val tag = "BuyMatch"

        fun newInstance(
            matchData: MatchData,
            callback:() -> Unit = {},
        ): BuyMatchDialogFragment {
            val fragment = BuyMatchDialogFragment()
            fragment.callback = callback
            fragment.matchData = matchData
            //args.putSerializable("", )
            return fragment
        }
    }
}
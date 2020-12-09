package com.example.brainer.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainer.LevelObject
import com.example.brainer.R
import com.example.brainer.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private lateinit var viewModel: TitleViewModel
    private lateinit var buttons: Array<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title, container, false
        )

        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        buttons =
            arrayOf(binding.buttonEasyLevel, binding.buttonMiddleLevel, binding.buttonHardLevel)

        for (now: Button in buttons) {
            now.setOnClickListener() {
                val btn: Button = it as Button
                /*val max: Int
                val min: Int
                val time: Int*/
                val mod: Int

                when (btn.id) {
                    buttons[0].id -> mod = 1
                    buttons[1].id -> mod = 2
                    else -> mod = 3
                }

                //var levelObject: LevelObject = LevelObject(min, max, time)
                val bundle: Bundle = Bundle()
                bundle.putInt("mod", mod)

                findNavController().navigate(R.id.action_titleFragment_to_gameFragment, bundle)

            }
        }


        return binding.root
    }

    /*fun onClickStartGame(view: View) {
        val btn: Button = view as Button
        val max: Int
        val min: Int
        val time: Long

        when (btn.id) {
            buttons[0].id -> {
                min = 5
                max = 30
                time = 30_000L
            }
            buttons[1].id -> {
                min = 33
                max = 99
                time = 45_000L
            }
            buttons[2].id -> {
                min = 101
                max = 499
                time = 60_000L
            }
        }


    }*/


}
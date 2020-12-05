package com.example.brainer.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.brainer.R
import com.example.brainer.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    val rand = (0..30).random()

    val viewModel: GameViewModel by lazy {
        ViewModelProvider(this).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val binding= DataBindingUtil<Fragm>.inflate(inflater, R.layout.fragment_game, container, false)
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        val bundle: Bundle? = this.arguments

        binding.textViewVar0.setText(bundle?.getInt("min").toString())
        binding.textViewVar1.setText(bundle?.getInt("max").toString())



        return binding.root
    }



}
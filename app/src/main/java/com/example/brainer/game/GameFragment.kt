package com.example.brainer.game

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainer.R
import com.example.brainer.databinding.FragmentGameBinding


class GameFragment : Fragment() {


    private lateinit var viewModelFactory: GameViewModelFactory
    private lateinit var viewModel: GameViewModel
    private lateinit var buttons: Array<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val binding= DataBindingUtil<>.inflate(inflater, R.layout.fragment_game, container, false)
        val binding: FragmentGameBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game, container, false
        )

        val bundle: Bundle? = this.arguments
        val mod = bundle!!.getInt("mod")

        viewModelFactory = GameViewModelFactory(mod)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel

        buttons = arrayOf(
            binding.textViewVar0, binding.textViewVar1,
            binding.textViewVar2, binding.textViewVar3
        )

        binding.lifecycleOwner = this

        for (now: TextView in buttons) {
            now.setOnClickListener {
                if (now.text.toString().toInt() == viewModel.rightAnswer.value) {
                    viewModel.onCorrect()
                    Toast.makeText(this.activity, getString(R.string.right), Toast.LENGTH_SHORT)
                        .show()
                    beginGame()
                } else {
                    Toast.makeText(this.activity, getString(R.string.wrong), Toast.LENGTH_SHORT)
                        .show()
                    beginGame()
                }
            }
        }

        viewModel.gameIsStarted.observe(viewLifecycleOwner, {
            if (it) {
                setNumbers()
                setVisibility(binding.textViewTimerToStartGame, false)
                setVisibility(binding.textViewQuestion, true)
                setVisibility(binding.textViewScore, true)
                setVisibility(binding.textViewTimer, true)
            }
        })

        viewModel.eventGAmeFinished.observe(viewLifecycleOwner, {
            if (it) {
                val bun: Bundle = Bundle()
                bun.putInt("score", viewModel.score.value!!)
                findNavController().navigate(R.id.action_gameFragment_to_scoreFragment, bun)
                viewModel.gameIsFinished()
            }
        })

        viewModel.context = this.requireContext()

        return binding.root
    }

    private fun beginGame() {
        viewModel.playGame()
        setNumbers()
    }

    private fun setVisibility(text: TextView, isVisible: Boolean) {
        if (isVisible)
            text.visibility = View.VISIBLE
        else
            text.visibility = View.INVISIBLE
    }

    private fun setNumbers() {
        for (i in 0..buttons.lastIndex) {
            buttons[i].text = viewModel.arrayOfAnswers[i].toString()
        }
    }

}
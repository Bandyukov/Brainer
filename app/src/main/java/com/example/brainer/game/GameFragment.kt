package com.example.brainer.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        val bundle: Bundle? = this.arguments
        val mod = bundle!!.getInt("mod")

        viewModelFactory = GameViewModelFactory(mod)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel

        buttons = arrayOf(binding.textViewVar0, binding.textViewVar1,
            binding.textViewVar2, binding.textViewVar3)

        beginGame()

        binding.textViewQuestion.setText(viewModel.question.value)
        binding.textViewQuestion.visibility = View.VISIBLE

        binding.lifecycleOwner = this

        for (now: TextView in buttons) {
            now.setOnClickListener {
                if(now.text.toString().toInt() == viewModel.rightAnswer.value) {
                    Toast.makeText(this.activity, "Right", Toast.LENGTH_SHORT).show()
                    beginGame()
                } else {
                    Toast.makeText(this.activity, "Wrong", Toast.LENGTH_SHORT).show()
                    beginGame()
                }

            }
        }

        return binding.root
    }

    private fun beginGame() {
        viewModel.playGame()
        for (i in 0..buttons.lastIndex) {
            buttons[i].setText(viewModel.arrayOfAnswers[i].toString())
        }
    }

}
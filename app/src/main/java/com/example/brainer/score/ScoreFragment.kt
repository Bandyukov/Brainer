package com.example.brainer.score

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainer.R
import com.example.brainer.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding = inflate(inflater,
            R.layout.fragment_score,
            container,
            false)

        val bundle = this.arguments
        if (bundle != null) {
            val score = bundle.getInt("score")
            scoreViewModelFactory = ScoreViewModelFactory(score)
            viewModel = ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)

            val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.requireContext())
            binding.scoreViewModel = viewModel

            binding.textViewEasyRecord.text = preferences.getInt("easy", 0).toString()
            binding.textViewMiddleRecord.text = preferences.getInt("middle", 0).toString()
            binding.textViewHardRecord.text = preferences.getInt("hard", 0).toString()

            binding.button.setOnClickListener {
                findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)
            }
        }


        return binding.root
    }

}
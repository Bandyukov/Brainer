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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainer.R
import com.example.brainer.adapters.RecordAdapter
import com.example.brainer.databinding.FragmentScoreBinding
import com.example.brainer.helperObjects.Record

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

            val records: List<Record> = List(3) {
                val s: String
                when (it) {
                    0 -> s = "easy"
                    1 -> s = "middle"
                    else -> s = "hard"
                }
                Record(it + 1, preferences.getInt(s, 0))
            }

            val adapter = RecordAdapter(records)
            binding.RecyclerViewRecordsForScoreFragment.layoutManager = LinearLayoutManager(this.context)
            binding.RecyclerViewRecordsForScoreFragment.adapter = adapter

            binding.button.setOnClickListener {
                it.findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)
            }
        }


        return binding.root
    }

}
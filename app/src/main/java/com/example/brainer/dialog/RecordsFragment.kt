package com.example.brainer.dialog

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainer.R
import com.example.brainer.adapters.RecordAdapter
import com.example.brainer.databinding.FragmentRecordsBinding
import com.example.brainer.helperObjects.Record


class RecordsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecordsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_records,
            container,
            false)

        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.requireContext())

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
        binding.recyclerViewRecords.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewRecords.adapter = adapter

        return binding.root
    }


}
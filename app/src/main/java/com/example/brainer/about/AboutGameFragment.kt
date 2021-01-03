package com.example.brainer.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.brainer.R
import com.example.brainer.databinding.FragmentAboutGameBinding

class AboutGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAboutGameBinding = DataBindingUtil.inflate(
            inflater,  R.layout.fragment_about_game, container, false
        )


        return binding.root
    }

}
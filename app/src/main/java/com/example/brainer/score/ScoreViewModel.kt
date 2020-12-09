package com.example.brainer.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(points: Int): ViewModel() {

    private val _points_ = MutableLiveData<Int>()
    val points: LiveData<Int>
        get() = _points_

    init {

    }
}
package com.example.brainer.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(SCORE: Int): ViewModel() {

    private val _score_ = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score_

    init {
        _score_.value = SCORE

        /*val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        val s1: String = "easy"
        val s2: String = "middle"
        val s3: String = "hard"

        val easy = preferences.getInt(s1, 0)
        val middle = preferences.getInt(s2, 0)
        val hard = preferences.getInt(s3, 0)

        _easyRecord_.value = easy
        _middleRecordd_.value = middle
        _hardRecord_.value = hard*/
    }


}
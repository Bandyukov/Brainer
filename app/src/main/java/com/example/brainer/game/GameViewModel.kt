package com.example.brainer.game

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel(mod: Int) : ViewModel() {

    private val _level_ = MutableLiveData<Int>()
    val level: LiveData<Int>
        get() = _level_

    private val _score_ = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score_

    private val _min_ = MutableLiveData<Int>()
    val min: LiveData<Int>
        get() = _min_

    private val _max_ = MutableLiveData<Int>()
    val max: LiveData<Int>
        get() = _max_

    private val _time_ = MutableLiveData<Long>()
    val time: LiveData<Long>
        get() = _time_

    private val _currentTime_ = MutableLiveData<String>()
    val currentTime: LiveData<String>
        get() = _currentTime_

    private val _timeBeforeStart_ = MutableLiveData<String>()
    val timeBeforeStart: LiveData<String>
        get() = _timeBeforeStart_

    private val _gameIsStarted_ = MutableLiveData<Boolean>()
    val gameIsStarted: LiveData<Boolean>
        get() = _gameIsStarted_

    private val _rightAnswer_ = MutableLiveData<Int>()
    val rightAnswer: LiveData<Int>
        get() = _rightAnswer_

    private val _question_ = MutableLiveData<String>()
    val question: LiveData<String>
        get() = _question_

    public val arrayOfAnswers: Array<Int> = Array(4) {0}

    private val _eventGameFinished_ = MutableLiveData<Boolean>()
    val eventGAmeFinished: LiveData<Boolean>
        get() = _eventGameFinished_

    public lateinit var context: Context


    init {
        when (mod) {
            1 -> setLevelSettings(1, 5, 30, 30_000L)
            2 -> setLevelSettings(2, 11, 50, 50_000L)
            else -> setLevelSettings(3, 101, 499, 70_000L)
        }

        _gameIsStarted_.value = false
        _eventGameFinished_.value = false
        _score_.value = 0

        val timerStartGame = object : CountDownTimer(time.value!!, 1_000) {

            override fun onTick(p0: Long) {
                var seconds = (p0 / 1_000)
                val minutes = seconds / 60
                seconds %= 60
                _currentTime_.value =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                _eventGameFinished_.value = true

                val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                val score1: Int
                val score2: Int
                val score3: Int

                val s1: String = "easy"
                val s2: String = "middle"
                val s3: String = "hard"
                val s: String

                s = when (_level_.value) {
                    1 -> s1
                    2 -> s2
                    else -> s3
                }

                if (_score_.value!! > preference.getInt(s, 0))
                    preference.edit().putInt(s, _score_.value!!).apply()
            }
        }

        val timerPrepareGame = object : CountDownTimer(4_000, 1_000) {

            override fun onTick(p0: Long) {
                val seconds = (p0 / 1_000)
                _timeBeforeStart_.value = String.format(Locale.getDefault(), "%d", seconds)
            }

            override fun onFinish() {
                timerStartGame.start()
                _gameIsStarted_.value = true
            }
        }

        timerPrepareGame.start()
        playGame()

    }

    private fun setLevelSettings(MOD: Int, MIN_VALUE: Int, MAX_VALUE: Int, TIME: Long) {
        _level_.value = MOD
        _min_.value = MIN_VALUE
        _max_.value = MAX_VALUE
        _time_.value = TIME
    }

    private fun generateQuestion() {
        val a = (_min_.value!!.._max_.value!!).random()
        val b = (_min_.value!!.._max_.value!!).random()
        val mark = (0..1).random()

        val positive: Boolean = mark == 1
        val question: String
        val rightAnswer: Int

        if (positive) {
            rightAnswer = a + b
            question = "$a + $b"
        } else {
            rightAnswer = a - b
            question = "$a - $b"
        }

        _question_.value = question
        _rightAnswer_.value = rightAnswer

    }

    private fun generateWrongAnswer(): Int {
        var a: Int = (_min_.value!!.._max_.value!!).random()
        while (a == _rightAnswer_.value)
            a = (_min_.value!!.._max_.value!!).random()
        return a
    }

    private fun generateWrongAnswer(lev: Int): Int {
        val ans: Int = _rightAnswer_.value!!
        var a: Int = ((ans - (lev - 1) * 10)..(ans + (lev + 1) * 10)).random()
        while (a == ans)
            a = ((ans - (lev - 1) * 10)..(ans + (lev + 1) * 10)).random()
        return a
    }

    public fun playGame() {
        generateQuestion()
        val rightAnswerPosition = (0..3).random()

        //var flag: Boolean = true

        for (i in 0..arrayOfAnswers.lastIndex)
            if (i != rightAnswerPosition)
                if (_level_.value == 1)
                    arrayOfAnswers[i] = generateWrongAnswer()
                else
                    /*{
                    var value: Int
                    if (flag) {
                        val a = (0..1).random()
                        if (a == 1)
                            value = _rightAnswer_.value!! - 10
                        flag = false
                    }*/
                    arrayOfAnswers[i] = generateWrongAnswer(_level_.value!!)
            else
                arrayOfAnswers[i] = _rightAnswer_.value!!

    }

    public fun onCorrect() {
        _score_.value = _score_.value?.plus(1)
    }

    public fun gameIsFinished() {
        _eventGameFinished_.value = false
    }


}




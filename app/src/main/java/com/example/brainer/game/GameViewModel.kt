package com.example.brainer.game

import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainer.LevelObject
import com.example.brainer.title.TitleViewModel
import java.util.*

class GameViewModel(mod: Int) : ViewModel() {

    private val _level_ = MutableLiveData<Int>()
    val level: LiveData<Int>
        get() = _level_

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

    public lateinit var arrayOfAnswers: Array<Int>


    init {
        when (mod) {
            1 -> setLevelSettings(1, 5, 30, 30_000L)
            2 -> setLevelSettings(2, 33, 99, 45_000L)
            else -> setLevelSettings(3, 101, 499, 60_000L)
        }

        _gameIsStarted_.value = false

        val timerStartGame = object : CountDownTimer(time.value!!, 1_000) {

            override fun onTick(p0: Long) {
                var seconds = (p0 / 1_000)
                val minutes = seconds % 60
                seconds %= 60
                _currentTime_.value =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
               // Toast.makeText(GameFragment::class.java, "ENDGAME", Toast.LENGTH_SHORT).show()
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
        val ans = _rightAnswer_.value!!
        var a: Int = ((ans - lev * 10)..(ans + lev * 10)).random()
        while (a == ans)
            a = ((ans - lev * 10)..(ans + lev * 10)).random()
        return a
    }

    public fun playGame() {
        generateQuestion()
        val rightAnswerPosition = (0..3).random()

        arrayOfAnswers = Array(4) {
            if (it != rightAnswerPosition) generateWrongAnswer() else _rightAnswer_.value!!
        }

    }

    public fun onButtonPressed() {}


}




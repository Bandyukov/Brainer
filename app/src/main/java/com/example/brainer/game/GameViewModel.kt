package com.example.brainer.game

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainer.LevelObject
import com.example.brainer.title.TitleViewModel

class GameViewModel(bundle: Bundle) : ViewModel() {
    private val start : Int = bundle.getInt(LevelObject.s)

    private val rand = (start..1000).random()


}
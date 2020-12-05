package com.example.brainer.game

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GameViewModelFactory(private val bundle: Bundle) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(bundle) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
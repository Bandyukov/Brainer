package com.example.brainer

class LevelObject(private var min: Int, private var max: Int, private var time: Int) {

    fun getMin(): Int = min
    fun setMin(value: Int) {min = value}
    companion object {
        val s: String = "min"
    }

}
package com.example.geometricassistant.ui.polygon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Math.pow
import java.util.Arrays
import kotlin.math.pow

class PolygonViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "?"
    }
    val cutValue: LiveData<String> = _text
}
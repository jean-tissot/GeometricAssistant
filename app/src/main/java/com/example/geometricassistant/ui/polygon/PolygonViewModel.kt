package com.example.geometricassistant.ui.polygon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PolygonViewModel : ViewModel() {

    // number of decimals to display for cutValue
    private var roundMode = "%.3f"

    var nbSides: Int? = null
        set(value) {
            field = value
            refreshCutValue()
        }

    var radius: Int? = null
        set(value) {
            field = value
            refreshCutValue()
        }

    private val _text = MutableLiveData<String>().apply {
        value = "?"
    }
    val cutValue: LiveData<String> = _text

    private fun refreshCutValue() {
        if(nbSides!=null && radius!=null) {
            val ratio = Math.PI / nbSides!!
            val cutValue = radius!! * kotlin.math.tan(ratio) / (1 + kotlin.math.cos(ratio))
            _text.value = String.format(roundMode, cutValue)
        } else {
            _text.value = "?"
        }
    }
}
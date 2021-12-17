package org.wit.accommodation.ui.List

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hope You Found What You Were Looking :)"
    }
    val text: LiveData<String> = _text
}
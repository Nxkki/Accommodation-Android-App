package org.wit.accommodation.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Nikki Osita Second Assignment, Hope you enjoyed!"
    }
    val text: LiveData<String> = _text
}
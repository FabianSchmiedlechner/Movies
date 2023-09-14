package com.example.movies

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    open fun onAttached() {
        //NO-OP
    }

    open fun onDetached() {
        //NO-OP
    }
}
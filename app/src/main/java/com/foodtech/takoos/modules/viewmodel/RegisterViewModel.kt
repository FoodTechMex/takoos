package com.foodtech.takoos.modules.viewmodel

import androidx.lifecycle.LiveData
import com.foodtech.takoos.base.BaseViewModel
import com.foodtech.takoos.firebase.Authentication

class RegisterViewModel : BaseViewModel() {

    private var authentication = Authentication()

    private val uid: LiveData<String> get() = authentication.uid

    val msgAuth: LiveData<String> get() = authentication.msg
    val msgFireStore = "Registro exitoso!"

    val registerAuthSuccess: LiveData<Boolean> get() = authentication.registerSuccess
    val registerFireStoreSuccess  = false

    val progressAuth: LiveData<Boolean> get() = authentication.progress
    var progressFireStore= false

    fun registerWithEmailAndPassword(email: String, pass: String) {
        authentication.registerUser(email, pass)
    }

    fun registerInFireStore(){
        progressFireStore=true
    }
}
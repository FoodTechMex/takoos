package com.foodtech.takoos.firebase

import com.google.firebase.auth.FirebaseUser

interface TokenAcessFirebaseCallback : BaseFirebaseCallback {
    fun onSucessGetToken(tokenA: String)
}

interface StatusAuthFirebaseCallback : BaseFirebaseCallback {
    fun onGetDataUser(user: FirebaseUser)
}


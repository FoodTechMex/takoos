package com.foodtech.takoos.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthenticationAPI {

    private var mFirebaseAuth = FirebaseAuth.getInstance()

    fun getInstance(): FirebaseAuth {
        return mFirebaseAuth
    }

    fun getCurrentUser(): FirebaseUser? {
        return mFirebaseAuth.currentUser
    }

}
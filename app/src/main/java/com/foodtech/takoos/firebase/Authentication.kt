package com.foodtech.takoos.firebase

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodtech.takoos.local.PreferencesHelper
import com.foodtech.takoos.modules.models.ErrorT
import com.foodtech.takoos.modules.models.KeyUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Authentication {

    private var mAuthenticationAPI = FirebaseAuthenticationAPI

    val uid: LiveData<String> get() = _uid
    private val _uid = MutableLiveData<String>()

    val msg: LiveData<String> get() = _msg
    private val _msg = MutableLiveData<String>()

    val registerSuccess: LiveData<Boolean> get() = _registerSucess
    private val _registerSucess = MutableLiveData<Boolean>()

    val progress: LiveData<Boolean> get() = _progress
    private val _progress = MutableLiveData<Boolean>()

    private val EMAIL_FORMATED_ERROR = "The email address is badly formatted."
    private val EMAIL_NO_EXIST_ERROR =
        "There is no user record corresponding to this identifier. The user may have been deleted."
    private val PASSWORD_ERROR = "The password is invalid or the user does not have a password."

    init {
        setListener()
    }

    private fun setListener() {
        mAuthenticationAPI.getInstance().addAuthStateListener { getListener() }
    }

    private fun getListener(): FirebaseAuth.AuthStateListener? {
        return FirebaseAuth.AuthStateListener {
            val user: FirebaseUser? = mAuthenticationAPI.getCurrentUser()
            if (user != null) {
                PreferencesHelper.saveBoolean(KeyUser.LOGGED, true)
            } else {
                PreferencesHelper.saveBoolean(KeyUser.LOGGED, false)
            }
        }
    }

    fun getAuthWithCredenciales(email: String, pass: String, callback: StatusAuthFirebaseCallback) {
        mAuthenticationAPI.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mAuthenticationAPI.getInstance().currentUser?.let {
                        callback.onGetDataUser(it)
                    }
                }
            }.addOnFailureListener { e ->
                when (e.message) {
                    EMAIL_FORMATED_ERROR -> callback.onError("Email no válido")
                    EMAIL_NO_EXIST_ERROR -> callback.onError("Usuario no registrado")
                    PASSWORD_ERROR -> callback.onError("Usuario o contraseña no válido")
                    else -> callback.onError("error :" + e.message)
                }
            }
    }

    fun getToken(tokenCallback: TokenAcessFirebaseCallback) {
        if (mAuthenticationAPI.getInstance().currentUser != null) {
            mAuthenticationAPI.getInstance()
                .currentUser!!
                .getIdToken(true).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (task.result != null)
                            tokenCallback.onSucessGetToken(task.result!!.token!!)
                        else tokenCallback.onError("token null")
                    }
                }.addOnFailureListener { e -> tokenCallback.onError("token error: " + e.message) }
        }
    }

    fun registerUser(email: String, pass: String) {
        _progress.value = true
        mAuthenticationAPI.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { task ->
                val user = mAuthenticationAPI.getCurrentUser()
                _uid.value = user?.uid
                _msg.value = "registro exitoso"
            }.addOnFailureListener { e ->
                run {
                    _uid.value = ""
                    _msg.value = e.message
                }
            }.addOnCompleteListener {
                _progress.value = false
            }
    }


}
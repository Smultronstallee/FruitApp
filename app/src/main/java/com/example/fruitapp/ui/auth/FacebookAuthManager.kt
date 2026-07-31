package com.example.fruitapp.ui.auth

import android.app.Activity
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

object FacebookAuthManager {
    private val callbackManager = CallbackManager.Factory.create()

    init {
        Log.d("FB_MANAGER", "INSTANCE = ${hashCode()}")
    }

    fun login(activity: Activity, callback:(String?)-> Unit) {
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult) {
                    Log.d("FACEBOOK_LOGIN", "SUCCESS")
                    Log.d("FACEBOOK_LOGIN", result.accessToken.token)
                    callback(result.accessToken.token)
                }

                override fun onCancel() {
                    Log.d("FACEBOOK_LOGIN", "CANCEL")
                    callback(null)
                }

                override fun onError(error: FacebookException) {
                    Log.e("FACEBOOK_LOGIN", "ERROR", error)
                    callback(null)
                }
            })
        LoginManager.getInstance()
            .logInWithReadPermissions(activity, listOf("email", "public_profile"))
    }
    fun getCallbackManager() = callbackManager
}
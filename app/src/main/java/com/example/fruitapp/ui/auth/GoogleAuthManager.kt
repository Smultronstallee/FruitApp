package com.example.fruitapp.ui.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.fruitapp.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleAuthManager(private val context: Context) {
    private val credentialManager = CredentialManager.create(context)

    suspend fun signIn(): String{
        val option = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.GG_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(option)
            .build()

        val res = credentialManager.getCredential(context, request)

        val credential = res.credential

        if(credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ){
            val ggCredential = GoogleIdTokenCredential.createFrom(credential.data)
            return ggCredential.idToken
        }
        throw Exception("Google Login failed!")
    }

}
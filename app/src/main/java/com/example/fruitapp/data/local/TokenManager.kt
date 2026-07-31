package com.example.fruitapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth")
class TokenManager(
    private val context: Context
) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    val tokenFlow: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }

    suspend fun saveToken(token: String){
        context.dataStore.edit { pref ->
            pref[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String? {
        return context.dataStore.data.first()[TOKEN_KEY]
    }

    suspend fun clearToken(){
        context.dataStore.edit { pref ->
            pref.remove(TOKEN_KEY)
        }
    }
    }

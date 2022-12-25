package de.saringer.moviedemoapp.features.login.network

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "user_login_data")

class LoginRepository @Inject constructor(
    private val api: LoginApi,
    application: Application
) {
    private val dataStore = application.applicationContext.dataStore

    suspend fun getRequestToken() = api.getRequestToken()

    suspend fun getSessionIdForGuests() = api.getSessionIdForGuests()

    suspend fun getSessionIdWithUserData(
        username: String,
        password: String,
        requestToken: String
    ) = api.getSessionIdWithUserData(
        username = username, password = password, requestToken = requestToken
    )

    suspend fun deleteSession() = api.deleteSession()

    fun saveLoginData(username: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.edit { prefs ->
                prefs[LoginKeys.USERNAME] = username
                prefs[LoginKeys.PASSWORD] = password
            }
        }
    }

    fun getStoredUsername(): String? {
        var username: String? = null

        CoroutineScope(Dispatchers.Main).launch {
            dataStore.data.collect { preferences ->
                username = preferences[LoginKeys.USERNAME]
            }
        }
        return username
    }

    fun getStoredPassword(): String? {
        var password: String? = null

        CoroutineScope(Dispatchers.Main).launch {
            dataStore.data.collect { preferences ->
                password = preferences[LoginKeys.PASSWORD]
            }
        }
        return password
    }

    private object LoginKeys {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
    }
}


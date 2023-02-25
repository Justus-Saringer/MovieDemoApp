package de.saringer.moviedemoapp.features.login.network

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import de.saringer.moviedemoapp.features.login.network.domain.LoginSessionIdUser
import de.saringer.moviedemoapp.features.login.network.domain.LoginToken
import de.saringer.moviedemoapp.features.login.network.extension.toLoginSessionIdGuest
import de.saringer.moviedemoapp.features.login.network.extension.toLoginSessionIdUser
import de.saringer.moviedemoapp.features.login.network.extension.toLoginToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "user_login_data")

class LoginRepository @Inject constructor(
    private val api: LoginApi,
    application: Application
) {
    private val dataStore = application.applicationContext.dataStore

    suspend fun getSessionIdForGuests() = api.getSessionIdForGuests().toLoginSessionIdGuest()

    suspend fun getSessionIdWithUserData(
        username: String,
        password: String,
        requestToken: String
    ): LoginSessionIdUser? {
        return withContext(Dispatchers.IO) {
            runCatching {
                api.getSessionIdWithUserData(
                    username = username, password = password, requestToken = requestToken
                ).toLoginSessionIdUser()
            }.getOrNull()
        }
    }

    suspend fun deleteSession(currentSession: String) = withContext(Dispatchers.IO) {
        runCatching { api.deleteSession(currentSession) }
    }

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
        val getUsernameJob = CoroutineScope(Dispatchers.Main).launch {
            dataStore.data.collect { preferences ->
                username = preferences[LoginKeys.USERNAME]
            }
        }
        getUsernameJob.onJoin.let { return username }
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

    suspend fun getTokenData(): LoginToken? {
        return kotlin.runCatching {
             fetchToken()
        }.getOrNull()
    }

    private suspend fun fetchToken() = withContext(Dispatchers.IO) {
        runCatching {
            return@withContext api.getRequestToken().toLoginToken()
        }.getOrElse {
            return@withContext null
        }
    }

    private object LoginKeys {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
    }
}


package de.saringer.moviedemoapp.shared.state

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}

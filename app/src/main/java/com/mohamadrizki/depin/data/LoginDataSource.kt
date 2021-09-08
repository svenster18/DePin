package com.mohamadrizki.depin.data

import android.widget.Toast
import com.mohamadrizki.depin.data.model.LoggedInUser
import java.io.IOException
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val dummyUsername = "mohamadrizki8@gmail.com"
    private val dummyPassword = "r12k14n4k50l3h"

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            if (!username.equals(dummyUsername)) {
                return Result.Failed("Username Salah")
            }
            else if (!password.equals(dummyPassword)) {
                return Result.Failed("Password Salah")
            }
            else {
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Mohamad Rizki")
                return Result.Success(fakeUser)
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
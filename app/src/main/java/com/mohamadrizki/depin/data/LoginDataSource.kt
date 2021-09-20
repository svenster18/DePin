package com.mohamadrizki.depin.data

import android.widget.Toast
import com.mohamadrizki.depin.data.model.LoggedInUser
import com.mohamadrizki.depin.data.model.User
import java.io.IOException
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var username = "mohamadrizki8@gmail.com"
    private var password = "r12k14n4k50l3h"
    private var name = "Mohamad Rizki"

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            if (!username.equals(this.username) || username.isEmpty()) {
                return Result.Failed("Username Salah")
            }
            else if (!password.equals(this.password) || password.isEmpty()) {
                return Result.Failed("Password Salah")
            }
            else {
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), this.name)
                return Result.Success(fakeUser)
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Terjadi kesalahan masuk", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun register(user: User): Result<User> {
        try {
            // TODO: handle register process
            this.username = user.email
            this.password = user.password
            this.name = user.name
            return Result.Success(user)
        } catch (e: Throwable) {
            return Result.Error(IOException("Terjadi kesalahan pada pendaftaran akun", e))
        }
    }
}
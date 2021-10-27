package com.mohamadrizki.depin.data

import android.util.Log
import android.widget.Toast
import com.mohamadrizki.depin.data.model.LoggedInUser
import com.mohamadrizki.depin.data.model.User
import com.mohamadrizki.depin.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            val client = ApiConfig.getApiService().getUser()
            lateinit var result: Result<LoggedInUser>
            client.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val listUser = responseBody.userResponse
                            for (user in listUser) {
                                if (!username.equals(user.email) || username.isEmpty()) {
                                    result = Result.Failed("Username Salah")
                                    Log.e(TAG, "Username Salah")
                                }
                                else if (!password.equals(user.password) || password.isEmpty()) {
                                    result = Result.Failed("Password Salah")
                                    Log.e(TAG, "Password Salah")
                                }
                                else {
                                    val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), user.nama)
                                    result = Result.Success(fakeUser)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })
            Log.d(TAG, "$result")
            return result
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

    companion object {
        private const val TAG = "LoginDataSource"
    }
}
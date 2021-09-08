package com.mohamadrizki.depin.utils

import com.mohamadrizki.depin.data.Result
import com.mohamadrizki.depin.data.model.LoggedInUser
import com.mohamadrizki.depin.ui.login.LoggedInUserView
import com.mohamadrizki.depin.ui.login.LoginResult
import java.util.*

object DataDummy {

    val username = "mohamadrizki8@gmail.com"
    val password = "r12k14n4k50l3h"
    val displayName = "Mohamad Rizki"

    val loggedInUser = Result.Success(LoggedInUser(UUID.randomUUID().toString(), displayName))
    val loginResult = LoginResult(success = LoggedInUserView(displayName))
}
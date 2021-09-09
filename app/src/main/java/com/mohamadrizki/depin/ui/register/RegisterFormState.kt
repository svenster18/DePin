package com.mohamadrizki.depin.ui.register

data class RegisterFormState (
    val nameError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val idNumberError: Int? = null,
    val phoneNumberError: Int? = null,
    val isDataValid: Boolean = false
 )
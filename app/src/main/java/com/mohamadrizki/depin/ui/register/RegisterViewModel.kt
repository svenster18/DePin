package com.mohamadrizki.depin.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.data.LoginRepository
import com.mohamadrizki.depin.data.Result
import com.mohamadrizki.depin.data.model.User

class RegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(user: User) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.register(user)

        if (result is Result.Success) {
            _registerResult.value =
                RegisterResult(success = R.string.register_success)
        } else {
            _registerResult.value = RegisterResult(error = R.string.register_failed)
        }
    }

    fun registerDataChanged(user: User) {
        if (!isNameValid(user.name)) {
            _registerForm.value = RegisterFormState(nameError = R.string.invalid_name)
        } else if (!isEmailValid(user.email)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(user.password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (!isIdNumberValid(user.idNumber)) {
            _registerForm.value = RegisterFormState(idNumberError = R.string.invalid_id_number)
        } else if (!isPhoneNumberValid(user.phoneNumber)) {
            _registerForm.value = RegisterFormState(phoneNumberError = R.string.invalid_phone_number)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder name validation check
    private fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }

    // A placeholder username validation check
    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // A placeholder id number validation check
    private fun isIdNumberValid(idNumber: String): Boolean {
        return idNumber.length == 16 && idNumber.isNotBlank()
    }

    // A placeholder phone number validation check
    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank()
    }
}
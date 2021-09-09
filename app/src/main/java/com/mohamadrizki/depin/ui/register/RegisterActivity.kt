package com.mohamadrizki.depin.ui.register

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.data.model.User
import com.mohamadrizki.depin.databinding.ActivityRegisterBinding
import com.mohamadrizki.depin.ui.factory.LoginViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.edtName
        val username = binding.edtUsername
        val password = binding.edtPassword
        val idNumber = binding.edtIdNumber
        val phoneNumber = binding.edtTelpNumber
        val register = binding.btnRegister
        val loading = binding.loading


        registerViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(RegisterViewModel::class.java)

        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer

            // disable register button unless all data is valid
            register.isEnabled = registerState.isDataValid

            if (registerState.nameError != null) {
                name.error = getString(registerState.nameError)
            }
            if (registerState.usernameError != null) {
                username.error = getString(registerState.usernameError)
            }
            if (registerState.passwordError != null) {
                password.error = getString(registerState.passwordError)
            }
            if (registerState.idNumberError != null) {
                idNumber.error = getString(registerState.idNumberError)
            }
            if (registerState.phoneNumberError != null) {
                phoneNumber.error = getString(registerState.phoneNumberError)
            }
        })

        registerViewModel.registerResult.observe(this@RegisterActivity, Observer {
            val registerResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (registerResult.error != null) {
                showRegisterFailed(registerResult.error)
            }
            if (registerResult.success != null) {
                updateUiWithUser()

                setResult(Activity.RESULT_OK)

                //Complete and destroy register activity once successful
                finish()
            }
        })

        name.afterTextChanged {
            registerViewModel.registerDataChanged(
                User(
                    null,
                    name.text.toString(),
                    username.text.toString(),
                    password.text.toString(),
                    idNumber.text.toString(),
                    phoneNumber.text.toString()
                )
            )
        }

        username.afterTextChanged {
            registerViewModel.registerDataChanged(
                User(
                    null,
                    name.text.toString(),
                    username.text.toString(),
                    password.text.toString(),
                    idNumber.text.toString(),
                    phoneNumber.text.toString()
                )
            )
        }

        password.afterTextChanged {
            registerViewModel.registerDataChanged(
                User(
                    null,
                    name.text.toString(),
                    username.text.toString(),
                    password.text.toString(),
                    idNumber.text.toString(),
                    phoneNumber.text.toString()
                )
            )
        }

        idNumber.afterTextChanged {
            registerViewModel.registerDataChanged(
                User(
                    null,
                    name.text.toString(),
                    username.text.toString(),
                    password.text.toString(),
                    idNumber.text.toString(),
                    phoneNumber.text.toString()
                )
            )
        }

        phoneNumber.apply {
            afterTextChanged {
                registerViewModel.registerDataChanged(
                    User(
                        null,
                        name.text.toString(),
                        username.text.toString(),
                        password.text.toString(),
                        idNumber.text.toString(),
                        phoneNumber.text.toString()
                    )
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        registerViewModel.register(
                            User(
                                null,
                                name.text.toString(),
                                username.text.toString(),
                                password.text.toString(),
                                idNumber.text.toString(),
                                phoneNumber.text.toString()
                            )
                        )
                }
                false
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                registerViewModel.register(
                    User(
                        java.util.UUID.randomUUID().toString(),
                        name.text.toString(),
                        username.text.toString(),
                        password.text.toString(),
                        idNumber.text.toString(),
                        phoneNumber.text.toString()
                    )
                )
            }

        }
    }

    private fun updateUiWithUser() {
        val registerSuccess = getString(R.string.register_success)
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$registerSuccess",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showRegisterFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
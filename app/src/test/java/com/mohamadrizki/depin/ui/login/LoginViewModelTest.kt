package com.mohamadrizki.depin.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mohamadrizki.depin.data.LoginRepository
import com.mohamadrizki.depin.data.Result
import com.mohamadrizki.depin.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var observer: Observer<LoginResult>

    @Before
    fun setUp() {
        viewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun login() {
        val dummyUsername = DataDummy.username
        val dummyPassword = DataDummy.password

        val loggedInUser = DataDummy.loggedInUser
        val dummyLoginResult = DataDummy.loginResult
        val login = MutableLiveData<LoginResult>()
        login.value = dummyLoginResult

        `when`(loginRepository.login(dummyUsername, dummyPassword)).thenReturn(loggedInUser)
        viewModel.login(dummyUsername, dummyPassword)
        val loginResult = viewModel.loginResult.value
        verify(loginRepository).login(dummyUsername, dummyPassword)
        assertNotNull(loginResult)
        assertEquals(LoginResult(LoggedInUserView("Mohamad Rizki")), loginResult)

        viewModel.loginResult.observeForever(observer)
        verify(observer).onChanged(dummyLoginResult)
    }
}
package dev.zotov.prod_app.modules.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zotov.prod_app.data.interfaces.UsersRepository
import dev.zotov.prod_app.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    private val _state = MutableStateFlow<ProfileState>(ProfileState.UnAuthorized)
    val state: StateFlow<ProfileState> get() = _state

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> get() = _loginError

    private val _signupError = MutableStateFlow<String?>(null)
    val signupError: StateFlow<String?> get() = _signupError

    init {
        usersRepository.currentUser()?.let {
            _state.value = ProfileState.Authorized(it)
        }
    }

    fun signup(password: String) {
        _signupError.value = null

        if (password.isBlank()) {
            _signupError.value = "Введите пароль"
            return
        }

        viewModelScope.launch {
            val user = usersRepository.getRandomUser()?.copy(
                password = usersRepository.hashPassword(password.trim())
            )

            if (user != null) {
                usersRepository.save(user)
                usersRepository.setCurrentUser(user.username)
                _state.value = ProfileState.Authorized(user)
            } else {
                _signupError.value = "Не удалось получить юзера"
            }
        }
    }

    fun login(username: String, password: String) {
        _loginError.value = null
        val user = usersRepository.getByUsername(username)

        if (user == null) {
            _loginError.value = "Неверный логин или пароль"
            return
        }

        val passwordCorrect = usersRepository.comparePassword(
            hash = user.password,
            password = password,
        )

        if (passwordCorrect) {
            usersRepository.setCurrentUser(user.username)
            _state.value = ProfileState.Authorized(user)
        } else {
            _loginError.value = "Неверный логин или пароль"
        }
    }

    fun logout() {
        _state.value = ProfileState.UnAuthorized
        usersRepository.setCurrentUser(null)
    }
}

sealed class ProfileState {
    data object UnAuthorized : ProfileState()

    data class Authorized(
        val user: User,
    ) : ProfileState()
}
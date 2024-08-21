package dev.zotov.prod_app.modules.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import dev.zotov.prod_app.modules.profile.components.AuthForm
import dev.zotov.prod_app.modules.profile.components.ProfileView

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavController,
) {
    when (val state = viewModel.state.collectAsState().value) {
        is ProfileState.Authorized -> ProfileView(user = state.user, onLogout = viewModel::logout)
        is ProfileState.UnAuthorized -> AuthForm(profileViewModel = viewModel)
    }
}
package com.tuapp.motiva3.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthCredential

class UsuarioViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var loginStatus = mutableStateOf<Boolean?>(null)
    var loginError = mutableStateOf<String?>(null)

    // Login tradicional con email y contraseña
    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginStatus.value = true
                } else {
                    loginStatus.value = false
                    loginError.value = task.exception?.message
                }
            }
    }

    // Login con Google
    fun signInWithGoogleCredential(credential: AuthCredential, onSuccess: () -> Unit) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginStatus.value = true
                    onSuccess()  // Llamar al callback de éxito después de login exitoso
                } else {
                    loginStatus.value = false
                    loginError.value = task.exception?.message
                }
            }
    }
}

package pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuapp.motiva3.viewmodel.TallerViewModel
import com.tuapp.motiva3.viewmodel.UsuarioViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun LoginPage(viewModel: UsuarioViewModel, onLoginSuccess: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar Sesión", fontSize = 32.sp, color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Email
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Contraseña
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Inicio de Sesión
        Button(onClick = {
            viewModel.login(email.value, password.value)
        }) {
            Text(text = "Iniciar Sesión")
        }

        // Observa el resultado del inicio de sesión
        if (viewModel.loginStatus.value == true) {
            onLoginSuccess() // Llama al lambda si el inicio de sesión es exitoso
        } else if (viewModel.loginStatus.value == false) {
            Text(text = viewModel.loginError.value ?: "Error de inicio de sesión", color = Color.Red)
        }
    }
}


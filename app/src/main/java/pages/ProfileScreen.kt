package com.tuapp.motiva3.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.motiva33.R
import com.google.firebase.auth.FirebaseAuth
import com.tuapp.motiva3.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    usuarioViewModel: UsuarioViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    val user = FirebaseAuth.getInstance().currentUser
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Confirmación", fontWeight = FontWeight.Bold) },
            text = { Text("¿Estás seguro de que deseas cerrar sesión?") },
            confirmButton = {
                Button(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        usuarioViewModel.loginStatus.value = false
                        navController.navigate("login") {
                            popUpTo("profile") { inclusive = true }
                        }
                        showDialog.value = false
                    }
                ) { Text("Sí") }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) { Text("No") }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF1F1F1))
    ) {
        TopAppBar(
            title = { Text("Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Foto de perfil
        if (user?.photoUrl != null) {
            Image(
                painter = painterResource(id = R.drawable.sportlogo), // Reemplaza con el recurso que prefieras si no hay foto
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Icono de usuario",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre y correo del usuario
        if (user != null) {
            Text(
                text = "Hola, ${user.displayName ?: "Usuario"}!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.email ?: "Correo no disponible",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            Text(
                text = "No hay usuario autenticado",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para cerrar sesión
        Button(
            onClick = { showDialog.value = true },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Cerrar sesión",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Cerrar Sesión", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

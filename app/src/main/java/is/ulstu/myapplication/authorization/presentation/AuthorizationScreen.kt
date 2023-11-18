package `is`.ulstu.myapplication.authorization.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
internal fun AuthorizationScreen(model: AuthorizationScreenViewModel = hiltViewModel()) {
    val state by model.state.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        (state.error as? AuthorizationError.Error)?.message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "My Company", style = TextStyle(color = Purple40, fontSize = 40.sp))

        val remOnLoginChanged = remember { model::onLoginChanged }
        TextField(
            modifier = Modifier.padding(12.dp),
            value = state.login,
            onValueChange = remOnLoginChanged,
            placeholder = { Text(text = "Логин") },
        )

        val remOnPasswordChanged = remember { model::onPasswordChanged }
        val remVisualTransformation = remember { PasswordVisualTransformation() }
        TextField(
            modifier = Modifier.padding(12.dp),
            value = state.password,
            onValueChange = remOnPasswordChanged,
            placeholder = { Text(text = "Пароль") },
            visualTransformation = remVisualTransformation,
        )

        val remOnClick = remember { model::singIn }
        Button(onClick = remOnClick) {
            Text("Войти")
        }
    }
}

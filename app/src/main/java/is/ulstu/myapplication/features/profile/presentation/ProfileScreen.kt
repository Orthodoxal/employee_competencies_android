package `is`.ulstu.myapplication.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.ui.theme.Purple40
import `is`.ulstu.myapplication.ui.theme.Typography

@Composable
@Preview
internal fun ProfileScreen(model: ProfileScreenViewModel = hiltViewModel()) {
    val state by model.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (val userInfoState = state.userInfoState) {
                is UserInfoState.Loading -> CircularProgressIndicator()
                is UserInfoState.Loaded  -> {
                    Text(text = "My Company", style = TextStyle(color = Purple40, fontSize = 40.sp))

                    Spacer(modifier = Modifier.size(32.dp))

                    Text(
                        text = userInfoState.user.fullName,
                        style = Typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = userInfoState.user.department,
                        style = Typography.bodyLarge
                    )

                    Text(
                        text = userInfoState.user.position,
                        style = Typography.bodyLarge
                    )
                }
            }
        }

        val remOnClick = remember { model::logout }
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = remOnClick,
        ) {
            Text("Выйти")
        }
    }
}

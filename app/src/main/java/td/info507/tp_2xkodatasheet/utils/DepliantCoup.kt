package td.info507.tp_2xkodatasheet.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DepliantCoup(modifier: Modifier) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val tab = listOf("1")

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button (
            onClick = { isOpen = !isOpen },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B59B6)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Description")
        }

        AnimatedVisibility(visible = isOpen) {
            Box() {
                Text("Normaux")
                LazyColumn(){
                    items(tab) { i ->
                        Box() {

                        }
                    }
                }
            }
        }
    }
}
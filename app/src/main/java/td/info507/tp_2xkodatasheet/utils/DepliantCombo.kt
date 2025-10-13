package td.info507.tp_2xkodatasheet.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import td.info507.tp_2xkodatasheet.R
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.model.Champion

@Composable
fun DepliantCombo(modifier: Modifier, champion: Champion) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button (
            onClick = { isOpen = !isOpen },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.violet)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Combo")
        }

        AnimatedVisibility(visible = isOpen) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.violet))
                    .padding(12.dp)
            ) {
                champion.lCombo.combo.forEach { combo ->
                    Text(text = "-> ${combo.joinToString(" ")}", color = Color.LightGray)
                }
            }
        }
    }
}
package td.info507.tp_2xkodatasheet.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.R
import td.info507.tp_2xkodatasheet.model.Champion
import kotlin.collections.List

@Composable
fun DepliantCombo(modifier: Modifier, champion: Champion) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val comboList = champion.lCombo.combo as? List<List<String>> ?: emptyList()

    val comboIcons = mapOf(
        "light" to R.drawable.light,
        "medium" to R.drawable.medium,
        "heavy" to R.drawable.heavy,
        "down" to R.drawable.down,
        "plus" to R.drawable.plus,
        "s2" to R.drawable.s2
    )

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
                comboList.forEachIndexed { indexCombo, combo ->

                    Text(text = "Combo ${indexCombo + 1}", color = Color.White, modifier = Modifier.padding(bottom = 4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        combo.forEachIndexed { index, comboPart ->
                            if (index > 0) {
                                Text(
                                    text = "->",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }

                            val imgResId = comboIcons[comboPart.lowercase()]

                            if (imgResId != null && imgResId != 0) {
                                Image(
                                    painter = painterResource(id = imgResId),
                                    contentDescription = comboPart,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                )
                            } else {
                                Text(
                                    text = comboPart.uppercase(),
                                    color = Color.Red,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
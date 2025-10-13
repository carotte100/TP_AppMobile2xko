package td.info507.tp_2xkodatasheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme
import td.info507.tp_2xkodatasheet.utils.DepliantCoup
import td.info507.tp_2xkodatasheet.utils.DepliantDescr
import td.info507.tp_2xkodatasheet.utils.DepliantCombo

@Composable
fun PageCharacter(champion: Champion, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = champion.nom, color = Color.White)
            Button(onClick = onBack) {
                Text("Retour")
            }
        }

        Spacer(Modifier.height(12.dp))
        DepliantDescr(modifier = Modifier, champion = champion)
        DepliantCoup(modifier = Modifier, champion = champion)
        DepliantCombo(modifier = Modifier, champion = champion)

        Spacer(Modifier.height(20.dp))
        Text(text = "Coups :", color = Color.White)
        champion.lCoup.lCoup.forEach { coup ->
            Text(text = "• ${coup.nom} (${coup.type})", color = Color.LightGray)
        }

        Spacer(Modifier.height(12.dp))
        Text(text = "Combos :", color = Color.White)
        champion.lCombo.combo.forEach { combo ->
            Text(text = "→ ${combo.joinToString(" ")}", color = Color.LightGray)
        }
    }
}


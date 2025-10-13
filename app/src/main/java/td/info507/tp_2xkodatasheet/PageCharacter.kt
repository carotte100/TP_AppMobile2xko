package td.info507.tp_2xkodatasheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.model.Champion
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

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Nom champion
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(R.color.vert))
                    .padding(8.dp),
                text = champion.nom,
                color = Color.White
            )

            // bouton retour
            Button(
                onClick = onBack,
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.cancel),
                    contentDescription = "Retour",
                    modifier = Modifier.fillMaxSize()
                )
            }



        }

        Spacer(Modifier.height(12.dp))
        DepliantDescr(modifier = Modifier, champion = champion)
        Spacer(Modifier.height(20.dp))
        DepliantCoup(modifier = Modifier, champion = champion)
        Spacer(Modifier.height(20.dp))
        DepliantCombo(modifier = Modifier, champion = champion)
    }
}


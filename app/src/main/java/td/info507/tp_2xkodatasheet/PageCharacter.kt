package td.info507.tp_2xkodatasheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.utils.DepliantCoup
import td.info507.tp_2xkodatasheet.utils.DepliantDescr
import td.info507.tp_2xkodatasheet.utils.DepliantCombo

@Composable
fun PageCharacter(champion: Champion, onBack: () -> Unit) {
    val context = LocalContext.current
    val imgName = champion.nom.lowercase() // nom de l'image
    val imgResId = context.resources.getIdentifier(imgName, "drawable", context.packageName)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.noir)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 32.dp) // Ajout de marge en bas
    ) {
        item {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.noir)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(32.dp))

                // Header avec image de fond et contenu au-dessus
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    // Image de fond
                    val context = LocalContext.current
                    val imgName = champion.nom.lowercase()
                    val imgResId =
                        context.resources.getIdentifier(imgName, "drawable", context.packageName)

                    if (imgResId != 0) {
                        Image(
                            painter = painterResource(id = imgResId),
                            contentDescription = "Fond du header",
                            contentScale = ContentScale.Crop, // prend tout l'espace
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x66000000)) // noir transparent
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = champion.nom,
                            color = Color.White,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(colorResource(R.color.vert).copy(alpha = 0.8f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )

                        Button(
                            onClick = onBack,
                            shape = RoundedCornerShape(50.dp),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.cancel),
                                contentDescription = "Retour",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(Modifier.height(12.dp))
            DepliantDescr(modifier = Modifier.padding(horizontal = 16.dp), champion = champion)
        }
        item {
            Spacer(Modifier.height(12.dp))
            DepliantCoup(modifier = Modifier.padding(horizontal = 16.dp), champion = champion)
        }
        item {
            Spacer(Modifier.height(12.dp))
            DepliantCombo(modifier = Modifier.padding(horizontal = 16.dp), champion = champion)
        }
    }
}
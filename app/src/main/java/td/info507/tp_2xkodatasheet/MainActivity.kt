package td.info507.tp_2xkodatasheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP_2XKOdatasheetTheme {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(colorResource(id = R.color.noir))
                ) {
                    Column {
                        searchBar()
                        characterList()
                    }
                }
            }
        }
    }
}

@Composable
fun searchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(R.color.vert))
        ) {
            Text("filtre")
        }
        Column (
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(R.color.vert))
        ) {
            Text("barre de recherche")
        }
    }
}

@Composable
fun characterList() {
    Column {
        val tab = intArrayOf(0, 1, 2, 3)
        val listChar = PageCharacter()

        for (i in tab) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Yellow)
                    .clickable { listChar.ShowCharacter() }
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Nom du perso $i")
                    Text("Image du perso $i")
                }
            }
        }
    }
}


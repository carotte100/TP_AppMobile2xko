package td.info507.tp_2xkodatasheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP_2XKOdatasheetTheme {
                searchBar()
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
        ) {
            Text("filtre")
        }
        Column (
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text("barre de recherche")
        }
    }
}

@Composable
fun characterList(){
    Row {
        // TODO ajouter un stockage de personnage
        // boucle for
        // showCharater()
    }
}
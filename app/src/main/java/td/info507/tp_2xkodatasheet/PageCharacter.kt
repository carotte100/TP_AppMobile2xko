package td.info507.tp_2xkodatasheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme

class PageCharacter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP_2XKOdatasheetTheme {
                AffichePerso()
            }
        }
    }
}

@Composable
fun AffichePerso(/*Nom: String*/)
{

}
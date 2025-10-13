package td.info507.tp_2xkodatasheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_2XKOdatasheetTheme {
                td.info507.tp_2xkodatasheet.ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    // état pour stocker l'image de l'utilisateur
    var profileImage by remember { mutableStateOf<ImageBitmap?>(null) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(0.25f)
                .height(100.dp)
        ) {
            if (profileImage != null) {
                /*UserImage(profileImage!!)*/
            } else {
                /*Image(
                    painter = painterResource(R.drawable.ic_user),
                    modifier = Modifier.size(80.dp).padding(8.dp),
                    contentDescription = "Image du profil"
                )*/
            }
        }

        Column {
            Row {
                Text(text = "Pseudo :")
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
            // les 3 boutons jaunes
            Row(modifier = Modifier.fillMaxWidth()) {
                val modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Yellow, shape = CircleShape)

                // bouton caméra
                /*CameraButton(modifier) { bitmap ->
                    profileImage = bitmap
                }*/

                IconButton(onClick = {}, modifier = modifier) {
                    /*Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "Galerie",
                        tint = Color.White
                    )*/
                }
                IconButton(onClick = {}, modifier = modifier) {
                    /*Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "Messagerie",
                        tint = Color.White
                    )*/
                }
            }
        }
//        StorageColumn(modifier = Modifier.fillMaxWidth(),
//            context = context)
    }
}
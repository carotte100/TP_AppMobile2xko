package td.info507.tp_2xkodatasheet.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DepliantDescr(modifier: Modifier) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF9B59B6))
                    .padding(12.dp)
            ) {
                Text(
                    text = "Equitis Romani autem esse filium criminis loco poni ab accusatoribus neque his iudicantibus oportuit neque defendentibus nobis.",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                AndroidView(
                    factory = {
                        YouTubePlayerView(it).apply {
                            enableAutomaticInitialization = false
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo("dQw4w9WgXcQ", 0f)
                                }
                            })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}

/*
les vidéos ne semble plus marcher, car youtube à l'air de bloquer l'affichage de certaines vidéos (peut-être des
droits d'auteurs), mais normalement ça devrait marcher (tout fonctionnait avant les vacances et maintenant plus rien)
 */

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import td.info507.tp_2xkodatasheet.R
import td.info507.tp_2xkodatasheet.model.Champion

@Composable
fun DepliantDescr(modifier: Modifier, champion: Champion) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { isOpen = !isOpen },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.violet)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Description")
        }

        AnimatedVisibility(visible = isOpen) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF9B59B6))
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.violet))
                    .padding(12.dp)
            ) {
                Text(
                    text = champion.description.description,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                AndroidView(
                    factory = {
                        YouTubePlayerView(it).apply {
                            enableAutomaticInitialization = false
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(champion.description.video, 0f)
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

    @Composable
    fun DepliantDescr(modifier: Modifier, champion: Champion) {
        var isOpen by remember { mutableStateOf(false) }
        val videoId = champion.description.video
        val lifecycleOwner = LocalLifecycleOwner.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { isOpen = !isOpen },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.violet)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Description")
            }

            AnimatedVisibility(visible = isOpen) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.violet))
                        .padding(12.dp)
                ) {
                    Text(
                        text = champion.description.description,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    AndroidView(
                        factory = { context ->
                            YouTubePlayerView(context).apply {
                                lifecycleOwner.lifecycle.addObserver(this)

                                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.loadVideo(videoId, 0f)
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
}
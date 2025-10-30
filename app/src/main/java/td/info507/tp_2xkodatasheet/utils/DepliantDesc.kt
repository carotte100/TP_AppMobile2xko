package td.info507.tp_2xkodatasheet.utils

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import td.info507.tp_2xkodatasheet.R
import td.info507.tp_2xkodatasheet.model.Champion

private const val TAG = "VIDEO_WEBVIEW_DEBUG"

@Composable
fun DepliantDescr(modifier: Modifier, champion: Champion) {
    var isOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val videoId = champion.description.video
    val embedUrl = remember(videoId) { "https://www.youtube.com/embed/$videoId?autoplay=0&fs=1&modestbranding=1" }


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
                /*
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.loadWithOverviewMode = true
                            settings.domStorageEnabled = true
                            settings.mediaPlaybackRequiresUserGesture = false
                            setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null)
                            webViewClient = WebViewClient()
                            webChromeClient = WebChromeClient()

                            Log.d(TAG, "WebView créé et chargé: $embedUrl")
                            loadUrl(embedUrl)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                */
            }
        }
    }
}
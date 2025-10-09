package td.info507.tp_2xkodatasheet

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.storage.ChampionJSonFileStorage
import td.info507.tp_2xkodatasheet.storage.ChampionStorage
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme
import td.info507.tp_2xkodatasheet.utils.loadJsonFromAssets
import td.info507.tp_2xkodatasheet.utils.readJsonFromAssets


class MainActivity : ComponentActivity() {
    private val jsonUrl = "http://51.68.91.213/gr-2-3/2XKO.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = ChampionJSonFileStorage(this)
        val championsState = mutableStateOf<List<Champion>>(emptyList())


        // Récupération du JSON depuis le serveur
        fetch2XKOJson(jsonUrl) { json ->
            json?.let {
                val champions = mutableListOf<Champion>()
                val keys = it.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val championJson = it.getJSONObject(key)
                    val champion = storage.jsonToObject(championJson)
                    champions.add(champion)
                }
                championsState.value = champions
                Log.d("Volley", "Champions chargés : ${champions.map { c -> c.nom }}")
            }
        }

        setContent {
            TP_2XKOdatasheetTheme {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(colorResource(id = R.color.noir))
                ) {
                    Column {
                        searchBar()
                        characterList(championsState.value)
                    }
                }
            }
        }
    }

    private fun fetch2XKOJson(url: String, onResult: (JSONObject?) -> Unit) {
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> onResult(response) },
            { error ->
                error.printStackTrace()
                onResult(null)
            }
        )
        queue.add(request)
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
        Column (
            modifier = Modifier
                .padding(8.dp),

            ) {
            TextField("", {}, shape = RoundedCornerShape(50.dp))
        }
        Column() {
            Button(
                onClick = {},
                shape = RoundedCornerShape(50)
            ) {
                Text("Img")
            }

        }
    }
}

@Composable
fun characterList(champions: List<Champion>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(champions) { champion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        color = colorResource(R.color.violet),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {  }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = champion.nom,
                    color = Color.Black,
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.vert),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 30.dp, vertical = 20.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(20.dp)
                ) {
                    Text("😊")
                }
            }
        }
    }
}

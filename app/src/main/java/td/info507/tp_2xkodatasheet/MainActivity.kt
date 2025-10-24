package td.info507.tp_2xkodatasheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.storage.ChampionJSonFileStorage
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme
import td.info507.tp_2xkodatasheet.utils.DepliantDescr

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
                val navController = rememberNavController()
                val champions = championsState.value

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        CharacterListScreen(champions = champions, navController = navController)
                    }

                    composable(
                        "details/{championName}",
                        arguments = listOf(navArgument("championName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("championName")
                        val champion = champions.find { it.nom == name }
                        champion?.let {
                            PageCharacter(champion = it, onBack = { navController.popBackStack() })
                        }
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
fun CharacterListScreen(champions: List<Champion>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.noir))
    ) {
        SearchBar()
        CharacterList(champions = champions, navController = navController)
    }
}

@Composable
fun SearchBar() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextField(value = "", onValueChange = {}, shape = RoundedCornerShape(50.dp))
        }
        Column {
            Button(onClick = {
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
            }, shape = RoundedCornerShape(50)) {
                Text("Img")
            }
        }
    }
}

@Composable
fun CharacterList(champions: List<Champion>, navController: NavController) {
    val context = LocalContext.current // On récupère le context ici pour charger les images

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(champions) { champion ->
            val imgName = champion.nom.lowercase()
            val imgResId = context.resources.getIdentifier(imgName, "drawable", context.packageName)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        color = colorResource(R.color.violet),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        // Page de champion
                        navController.navigate("details/${champion.nom}")
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // GAUCHE
                Text(
                    text = champion.nom,
                    color = Color.Black,
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.vert),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )

                // pousse a droite
                Spacer(modifier = Modifier.weight(1f))

                // DROITE
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .clip(RoundedCornerShape(50.dp))
                ) {

                    Image(
                        painter = painterResource(id = imgResId),
                        contentDescription = "Image de ${champion.nom}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
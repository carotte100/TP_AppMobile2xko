package td.info507.tp_2xkodatasheet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import td.info507.tp_2xkodatasheet.model.Champion
import td.info507.tp_2xkodatasheet.storage.ChampionJSonFileStorage
import td.info507.tp_2xkodatasheet.storage.loadImageFromInternalStorage
import td.info507.tp_2xkodatasheet.storage.loadImagePath
import td.info507.tp_2xkodatasheet.storage.loadPseudo
import td.info507.tp_2xkodatasheet.storage.saveImagePath
import td.info507.tp_2xkodatasheet.storage.saveImageToInternalStorage
import td.info507.tp_2xkodatasheet.storage.savePseudo
import td.info507.tp_2xkodatasheet.ui.theme.TP_2XKOdatasheetTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val storage = ChampionJSonFileStorage(this)
        val jsonString = intent.getStringExtra("champions_json") ?: "[]"
        val jsonArray = org.json.JSONArray(jsonString)
        val champions = mutableListOf<Champion>()

        for (i in 0 until jsonArray.length()) {
            val jsonObj = jsonArray.getJSONObject(i)
            champions.add(storage.jsonToObject(jsonObj))
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_2XKOdatasheetTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        ProfileScreen(
                            champions = champions,
                            navController = navController ,
                            onBack = { finish() }
                        )
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
}
@Composable
fun ProfileScreen(champions: List<Champion>, navController: NavController, onBack: () -> Unit) {

    val context = LocalContext.current

    var pseudo by rememberSaveable { mutableStateOf(loadPseudo( context)) }

    var imageBitmap by remember {
        mutableStateOf(loadImagePath(context)?.let { loadImageFromInternalStorage(it)?.asImageBitmap() })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(16.dp)
            .padding(top = 32.dp)
    ) {
        // Bouton retour
        BackButton(onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // Bloc photo + caméra
            ProfileImageSection(imageBitmap = imageBitmap,
                onImageCaptured = { capturedImage ->
                    imageBitmap = capturedImage

                    // Sauvegarder dans le stockage interne
                    val path = saveImageToInternalStorage(context, capturedImage.asAndroidBitmap(), "profile")
                    saveImagePath(context, path)
                }
            )

            Spacer(Modifier.height(40.dp))

            // Champ pseudo
            PseudoField(
                pseudo = pseudo,
                onPseudoChange = { newValue ->
                    pseudo = newValue
                    savePseudo(context, newValue)
                }
            )

            Spacer(Modifier.height(32.dp))

            // Liste des champions
            FavoriteList(champions, navController)
        }
    }
}

@Composable
fun BackButton(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {  // Box scope
        Button(
            onClick = onBack,
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(R.drawable.cancel),
                contentDescription = "Retour",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
fun ProfileImageSection(imageBitmap: ImageBitmap?, onImageCaptured: (ImageBitmap) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        UserImage(imageBitmap)
        CameraButton(onImageCaptured = onImageCaptured)
    }
}

@Composable
fun UserImage(imageBitmap: ImageBitmap?) {
    val modifier = Modifier
        .size(100.dp)
        .background(colorResource(R.color.violet), RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
    if (imageBitmap == null) {
        Box(contentAlignment = Alignment.Center, modifier = modifier) {
            Text("Photo", color = Color.White)
        }
    } else {
        Image(
            bitmap = imageBitmap,
            contentDescription = "Photo de profil",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PseudoField(pseudo: String, onPseudoChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .background(colorResource(R.color.vert),
            RoundedCornerShape(50.dp))
            .padding(horizontal = 40.dp, vertical = 12.dp)
    ) {
        BasicTextField(
            value = pseudo,
            onValueChange = onPseudoChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            ),
            decorationBox = { innerTextField ->
                if (pseudo.isEmpty()) {
                    Text(
                        text = "Pseudo",
                        color = Color.Black.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                innerTextField()
            },
        )
    }
}


@Composable
fun CameraButton(onImageCaptured: (ImageBitmap) -> Unit) {
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap -> bitmap?.let { onImageCaptured(it.asImageBitmap()) } }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) cameraLauncher.launch()
    }

    Box(
        modifier = Modifier
            .offset(y = 50.dp, x = 50.dp)
            .size(40.dp)
            .background(Color.White, shape = CircleShape)
            .clickable {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    cameraLauncher.launch()
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.plus),
            contentDescription = "Caméra",
            modifier = Modifier.size(28.dp), // taille du plus
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun FavoriteList(champions: List<Champion>, navController: NavController) {
    val context = LocalContext.current

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

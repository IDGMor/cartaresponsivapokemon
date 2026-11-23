package com.example.cartaresponsivapokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.cartaresponsivapokemon.ui.theme.CartaresponsivapokemonTheme
import kotlin.math.absoluteValue

data class PokemonCardState(
    val nameRes: Int,
    val hp: String,
    val imageRes: Int,
    val descriptionRes: Int,
    val attack1NameRes: Int,
    val attack1Damage: String,
    val attack2NameRes: Int,
    val attack2Damage: String,
    val weakness: String,
    val resistance: String,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartaresponsivapokemonTheme {
                GalleryScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen() {
    val pokemonList = listOf(
        PokemonCardState(
            nameRes = R.string.pikachu_name,
            hp = "60",
            imageRes = R.drawable.bicho,
            descriptionRes = R.string.pikachu_desc,
            attack1NameRes = R.string.pikachu_attack_1,
            attack1Damage = "55",
            attack2NameRes = R.string.pikachu_attack_2,
            attack2Damage = "35",
            weakness = stringResource(R.string.weakness_fighting),
            resistance = stringResource(R.string.resistance_steel),
        ),
        PokemonCardState(
            nameRes = R.string.charmander_name,
            hp = "70",
            imageRes = R.drawable.bicho,
            descriptionRes = R.string.charmander_desc,
            attack1NameRes = R.string.charmander_attack_1,
            attack1Damage = "50",
            attack2NameRes = R.string.charmander_attack_2,
            attack2Damage = "40",
            weakness = stringResource(R.string.weakness_water),
            resistance = stringResource(R.string.resistance_grass),
        ),
        PokemonCardState(
            nameRes = R.string.bulbasaur_name,
            hp = "80",
            imageRes = R.drawable.bicho,
            descriptionRes = R.string.bulbasaur_desc,
            attack1NameRes = R.string.bulbasaur_attack_1,
            attack1Damage = "45",
            attack2NameRes = R.string.bulbasaur_attack_2,
            attack2Damage = "30",
            weakness = stringResource(R.string.weakness_fire),
            resistance = stringResource(R.string.resistance_water),
        ),
        PokemonCardState(
            nameRes = R.string.squirtle_name,
            hp = "70",
            imageRes = R.drawable.bicho,
            descriptionRes = R.string.squirtle_desc,
            attack1NameRes = R.string.squirtle_attack_1,
            attack1Damage = "40",
            attack2NameRes = R.string.squirtle_attack_2,
            attack2Damage = "30",
            weakness = stringResource(R.string.weakness_electric),
            resistance = stringResource(R.string.resistance_steel),
        ),
        PokemonCardState(
            nameRes = R.string.snorlax_name,
            hp = "120",
            imageRes = R.drawable.bicho,
            descriptionRes = R.string.snorlax_desc,
            attack1NameRes = R.string.snorlax_attack_1,
            attack1Damage = "0",
            attack2NameRes = R.string.snorlax_attack_2,
            attack2Damage = "90",
            weakness = stringResource(R.string.weakness_fighting),
            resistance = stringResource(R.string.resistance_psychic),
        ),
    )

    val pagerState = rememberPagerState { pokemonList.size }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.gallery_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                // Counter badge
                Badge(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ) {
                    Text(
                        text = stringResource(R.string.card_counter, pagerState.currentPage + 1, pokemonList.size),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }

                // Horizontal Pager Carousel
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 48.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) { page ->
                    val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    val absoluteOffset = pageOffset.absoluteValue
                    val scale = lerp(0.85f, 1f, 1f - absoluteOffset.coerceIn(0f, 1f))
                    val alpha = lerp(0.5f, 1f, 1f - absoluteOffset.coerceIn(0f, 1f))

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                this.alpha = alpha
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        CrearCarta(pokemon = pokemonList[page])
                    }
                }

                // Page Indicator Dots
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    repeat(pokemonList.size) { iteration ->
                        val color = if (pagerState.currentPage == iteration) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        }
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CrearCarta(pokemon: PokemonCardState) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val cardAspectRatio = 0.714f

        val availableWidth = maxWidth
        val availableHeight = maxHeight

        val cardHeight: Dp
        val cardWidth: Dp

        if ((availableWidth / availableHeight) < cardAspectRatio) {
            cardWidth = availableWidth
            cardHeight = cardWidth / cardAspectRatio
        } else {
            cardHeight = availableHeight
            cardWidth = cardHeight * cardAspectRatio
        }

        Card(
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight)
                .border(
                    width = 4.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFD700),
                            Color(0xFFFFA500),
                            Color(0xFFDAA520),
                        ),
                    ),
                    shape = RoundedCornerShape(16.dp),
                ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF9C4),
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                // Header: Name and HP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(pokemon.nameRes),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF333333),
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.ps_label),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Red,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = pokemon.hp,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF333333),
                        )
                    }
                }

                // Image Container with inner border
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 6.dp)
                        .border(2.dp, Color(0xFFD4AF37), RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                ) {
                    Image(
                        painter = painterResource(id = pokemon.imageRes),
                        contentDescription = stringResource(R.string.image_desc),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                // Description / Flavor text
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(
                        text = stringResource(pokemon.descriptionRes),
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(8.dp),
                    )
                }

                // Attacks and Stats section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(pokemon.attack1NameRes),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = pokemon.attack1Damage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                    }
                    HorizontalDivider(color = Color(0xFFFFD54F), thickness = 1.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(pokemon.attack2NameRes),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = pokemon.attack2Damage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                    }
                }

                // Footer stats (Resistance / Weakness)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Text(
                        text = pokemon.weakness,
                        fontSize = 10.sp,
                        color = Color.Gray,
                    )
                    Text(
                        text = pokemon.resistance,
                        fontSize = 10.sp,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun CartaPreview() {
    CartaresponsivapokemonTheme {
        GalleryScreen()
    }
}

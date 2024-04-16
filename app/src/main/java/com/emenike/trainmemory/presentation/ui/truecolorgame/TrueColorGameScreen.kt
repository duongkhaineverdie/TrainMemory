package com.emenike.trainmemory.presentation.ui.truecolorgame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emenike.trainmemory.R
import com.emenike.trainmemory.presentation.ui.component.CustomDialog
import com.emenike.trainmemory.presentation.ui.theme.TrainMemoryTheme
import com.emenike.trainmemory.presentation.ui.truecolorgame.components.ItemButton
import org.koin.androidx.compose.koinViewModel
import kotlin.math.sqrt

@Composable
fun TrueColorGameScreen(navController: NavHostController) {
    val trainMemoryViewModel: TrainMemoryViewModel = koinViewModel()
    val uiState by trainMemoryViewModel.stateFlow.collectAsState()

    TrueColorGameScreen(
        modifier = Modifier
            .fillMaxSize(),
        levelGame = uiState.levelGame,
        onClickButton = trainMemoryViewModel::selectItemCorrect,
        onClickNewGame = trainMemoryViewModel::nextLevels,
        highScore = uiState.highScore,
        isDefeat = uiState.isDefeat,
        color = uiState.color,
        timeAlive = uiState.timeAlive,
        totalAlive = uiState.totalAlive,
        brushBackground = Brush.verticalGradient(
            listOf(
                Color(0xFFDDDDDD),
                Color(0xFF797979),
            )
        ),
        listNumberCorrect = uiState.listCorrect,
        listNumberSelected = uiState.listSelected
    )
}

@Composable
fun TrueColorGameScreen(
    modifier: Modifier = Modifier,
    levelGame: Int = 2,
    highScore: Int = 0,
    isDefeat: Boolean = false,
    color: Int = 0,
    timeAlive: Long = 5000,
    totalAlive: Long = 5000,
    brushBackground: Brush,
    listNumberCorrect: List<Int>,
    listNumberSelected: List<Int>,
    onClickNewGame: () -> Unit,
    onClickButton: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .background(brushBackground)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val levelColor = (sqrt(levelGame.toFloat()) + 1).toInt()
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = stringResource(id = R.string.regex_level_game, (levelGame - 1)),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .border(2.dp, Color.Black),
            columns = GridCells.Fixed(levelColor)
        ) {
            items(levelColor * levelColor) { i ->
                ItemButton(
                    modifier = Modifier.fillMaxSize(),
                    border = BorderStroke(0.2.dp, Color.Black),
                    color = if (listNumberCorrect.contains(i)) Color(color) else Color.Gray,
                    enabled = false,
                ) {
                    onClickButton(i)
                }
            }
        }

        LinearProgressIndicator(
            progress = { timeAlive / totalAlive.toFloat() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(10.dp)
                .border(2.dp, Color.Black),
            color = Color(color),
            strokeCap = StrokeCap.Square,
            trackColor = Color.LightGray
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .border(2.dp, Color.Black),
            columns = GridCells.Fixed(levelColor)
        ) {
            items(levelColor * levelColor) { i ->
                ItemButton(
                    modifier = Modifier.fillMaxSize(),
                    border = BorderStroke(0.2.dp, Color.Black),
                    color = if (listNumberSelected.contains(i)) Color(color) else Color.Gray,
                    enabled = !listNumberSelected.contains(i)
                ) {
                    onClickButton(i)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.regex_high_score, highScore),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                )
            )
        }
    }

    CustomDialog(
        showDialog = isDefeat,
        onDismissRequest = { /* no-op */ }) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.background(Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.img_defeat),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Card(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = onClickNewGame,
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3280D1),
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 30.dp
                )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    text = stringResource(id = R.string.new_game),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
@Preview(name = "NumberGuessingGame", showSystemUi = true)
private fun NumberGuessingGameScreenPreview() {
    TrainMemoryTheme {
        TrueColorGameScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF415670),
                            Color(0xFF162E4B),
                        )
                    )
                ),
            levelGame = 2,
            onClickButton = {/* no-op */ },
            onClickNewGame = {/* no-op */ },
            brushBackground = Brush.verticalGradient(
                listOf(
                    Color(0xFFDDDDDD),
                    Color(0xFF797979),
                )
            ),
            listNumberCorrect = listOf(0, 2, 3),
            listNumberSelected = listOf()
        )
    }
}


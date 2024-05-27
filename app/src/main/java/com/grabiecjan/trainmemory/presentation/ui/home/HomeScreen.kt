package com.grabiecjan.trainmemory.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.grabiecjan.trainmemory.R
import com.grabiecjan.trainmemory.domain.navigation.Destination
import com.grabiecjan.trainmemory.presentation.ui.theme.TrainMemoryTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        onClickAction = { navController.navigate(Destination.TrainMemoryScreen.fullRoute) },
        highScore = uiState.highScore
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit,
    highScore: Int = 0,
) {
    Image(
        painter = painterResource(id = R.drawable.img_background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(modifier.systemBarsPadding()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 80.sp,
                maxLines = 2,
                lineHeight = 70.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                modifier = Modifier
                    .border(2.dp, Color.White, shape = RoundedCornerShape(15.dp)),
                letterSpacing = 2.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 50.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.best_score_home),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp
                    )
                    Text(
                        text = stringResource(id = R.string.best_score_home),
                        style = MaterialTheme.typography.titleLarge.copy(
                            drawStyle = Stroke(
                                miter = 10f,
                                width = 5f,
                                join = StrokeJoin.Round
                            )
                        ),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp
                    )
                }
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = highScore.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp
                    )
                    Text(
                        text = highScore.toString(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            drawStyle = Stroke(
                                miter = 10f,
                                width = 5f,
                                join = StrokeJoin.Round
                            )
                        ),
                        color = Color.Blue,
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF644141)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 40.dp
                ),
                onClick = onClickAction,
                border = BorderStroke(1.dp, Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.start_game).uppercase(),
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleLarge.copy(
                            drawStyle = Stroke(
                                miter = 10f,
                                width = 10f,
                                join = StrokeJoin.Round
                            )
                        ),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 10.sp,
                        fontSize = 30.sp
                    )
                    Text(
                        text = stringResource(id = R.string.start_game).uppercase(),
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 10.sp,
                        fontSize = 30.sp
                    )
                }
            }
        }
    }


}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    TrainMemoryTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            onClickAction = {/* no-op */ },
        )
    }
}
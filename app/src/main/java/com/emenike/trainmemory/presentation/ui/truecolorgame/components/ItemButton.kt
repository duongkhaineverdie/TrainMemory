package com.emenike.trainmemory.presentation.ui.truecolorgame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemButton(
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(1.dp, Color.Black),
    color: Color = Color.Gray,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .aspectRatio(1f),
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = color
        ),
        border = border,
        contentPadding = PaddingValues(0.dp),
        enabled = enabled
    ) {/* no-op */ }
}

@Preview(
    name = "ItemButton",
    widthDp = 300,
    heightDp = 400,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PreviewButtonColor() {
    ItemButton(
        modifier = Modifier.fillMaxSize(),
        onClick = {/* no-op */ }
    )
}
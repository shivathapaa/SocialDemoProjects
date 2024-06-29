package com.shivathapaa.customtopappbar.components.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shivathapaa.customtopappbar.R

@Composable
fun RandomTopBarComposable(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_banner),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Profile banner of Shiva's github"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(vertical = 16.dp),
            text = "If you find this helpful don't forget to clap and share!\n Happy exploring! ;)",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp),
            text = "github.com/shivathapaa\nmedium.com/@shivathapaa",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}
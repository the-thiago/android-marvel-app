package com.thiago.marvelapp.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thiago.marvelapp.R
import com.thiago.marvelapp.ui.theme.MarvelAppTheme

@Composable
internal fun CharactersScreen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = stringResource(id = R.string.label_characters))
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "CharactersScreen")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CharactersPreview() {
    MarvelAppTheme {
        CharactersScreen()
    }
}
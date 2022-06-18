package com.thiago.marvelapp.presentation.about

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
internal fun AboutScreen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = stringResource(id = R.string.label_about))
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            Text(text = "AboutScreen")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AboutPreview() {
    MarvelAppTheme {
        AboutScreen()
    }
}
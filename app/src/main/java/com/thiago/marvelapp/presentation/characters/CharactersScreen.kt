package com.thiago.marvelapp.presentation.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.thiago.core.domain.model.Character
import com.thiago.marvelapp.R
import com.thiago.marvelapp.presentation.common.appPlaceholder

@Composable
internal fun CharactersScreen(viewModel: CharactersViewModel = hiltViewModel()) {
    val characters = viewModel.charactersPagingData().collectAsLazyPagingItems()
    CharactersScreen(
        state = viewModel.viewState,
        characters = characters,
        action = viewModel::submitAction
    )
}

@Composable
private fun CharactersScreen(
    state: CharactersViewState,
    characters: LazyPagingItems<Character>,
    action: (CharactersAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = stringResource(id = R.string.label_characters))
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val isRefreshing = characters.loadState.refresh == LoadState.Loading
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = { characters.refresh() },
                indicator = { refreshState, trigger ->
                    SwipeRefreshIndicator(
                        state = refreshState,
                        refreshTriggerDistance = trigger,
                        contentColor = MaterialTheme.colors.primary
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    isRefreshing -> CharactersPlaceholder()
                    characters.loadState.refresh is LoadState.Error -> {
                        LoadingCharactersError(onTryAgainClick = characters::refresh)
                    }
                    else -> CharactersLazyColumn(characters = characters)
                }
            }
        }
    }
}

@Composable
private fun CharactersLazyColumn(characters: LazyPagingItems<Character>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = characters) { item ->
            item?.let { Character(item) }
        }
        item {
            LoadingIndicator(
                isLoading = characters.loadState.append == LoadState.Loading
            )
            AppendCharactersError(
                showError = characters.loadState.append is LoadState.Error,
                onTryAgainClick = characters::retry
            )
        }
    }
}

@Composable
private fun LoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp))
        }
    }
}

@Composable
private fun AppendCharactersError(showError: Boolean, onTryAgainClick: () -> Unit) {
    if (showError) {
        Text(
            modifier = Modifier
                .clickable { onTryAgainClick() }
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.error_loading_more_try_again),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoadingCharactersError(onTryAgainClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dissatisfied),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.characters_loading_error),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onTryAgainClick() }) {
            Text(text = stringResource(id = R.string.characters_try_again))
        }
    }
}

@Composable
private fun Character(character: Character) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(230.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { /* TODO */ }
                .clip(shape = RoundedCornerShape(4.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = character.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun CharactersPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = false)
    ) {
        repeat(3) {
            CharacterPlaceholder()
        }
    }
}

@Composable
private fun CharacterPlaceholder() {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(230.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .appPlaceholder()
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .appPlaceholder()
                        .fillMaxWidth()
                )
            }
        }
    }
}
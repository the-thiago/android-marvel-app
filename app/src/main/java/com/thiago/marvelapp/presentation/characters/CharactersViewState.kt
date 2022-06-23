package com.thiago.marvelapp.presentation.characters

data class CharactersViewState(
    val isLoading: Boolean = false,
    val search: String = ""
) {
    companion object {
        val Empty = CharactersViewState()
    }
}
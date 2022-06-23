package com.thiago.marvelapp.presentation.characters

sealed class CharactersAction {
    data class Search(val query: String) : CharactersAction()
}
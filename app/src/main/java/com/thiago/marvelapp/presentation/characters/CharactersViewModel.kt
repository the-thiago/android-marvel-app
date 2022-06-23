package com.thiago.marvelapp.presentation.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.thiago.core.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
//    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    var viewState by mutableStateOf(CharactersViewState.Empty)
        private set

    val characters = getCharactersUseCase(
        GetCharactersUseCase.GetCharactersParams("", getPageConfig())
    ).cachedIn(viewModelScope)

    fun submitAction(action: CharactersAction) {
        viewModelScope.launch {
            when (action) {
                is CharactersAction.Search -> TODO()
            }
        }
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
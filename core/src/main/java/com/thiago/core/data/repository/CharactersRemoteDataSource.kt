package com.thiago.core.data.repository

import com.thiago.core.domain.model.CharacterPaging
import com.thiago.core.domain.model.Comic
import com.thiago.core.domain.model.Event

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchComics(characterId: Int): List<Comic>

    suspend fun fetchEvents(characterId: Int): List<Event>
}
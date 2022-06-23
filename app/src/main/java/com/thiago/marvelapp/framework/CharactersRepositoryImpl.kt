package com.thiago.marvelapp.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.thiago.core.data.repository.CharactersRemoteDataSource
import com.thiago.core.data.repository.CharactersRepository
import com.thiago.core.domain.model.Character
import com.thiago.core.domain.model.Comic
import com.thiago.core.domain.model.Event
import com.thiago.marvelapp.framework.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
//    private val database: AppDatabase
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

//    override fun getCachedCharacters(
//        query: String,
//        pagingConfig: PagingConfig
//    ): Flow<PagingData<Character>> {
//        return Pager(
//            config = pagingConfig,
////            remoteMediator = CharactersRemoteMediator(query, database, remoteDataSource)
//        ) {
//            database.characterDao().pagingSource()
//        }.flow.map { pagingData ->
//            pagingData.map {
//                Character(
//                    it.id,
//                    it.name,
//                    it.imageUrl
//                )
//            }
//        }
//    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.fetchComics(characterId)
    }

    override suspend fun getEvents(characterId: Int): List<Event> {
        return remoteDataSource.fetchEvents(characterId)
    }
}
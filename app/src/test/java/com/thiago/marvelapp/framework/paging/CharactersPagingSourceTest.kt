package com.thiago.marvelapp.framework.paging

import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thiago.core.data.repository.CharactersRemoteDataSource
import com.thiago.core.domain.model.Character
import com.thiago.marvelapp.factory.response.CharacterPagingFactory
import com.thiago.testing.MainCoroutineRule
import com.thiago.testing.model.CharacterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: CharactersRemoteDataSource

    private val dataWrapperResponseFactory = CharacterPagingFactory()

    private val characterFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setUp() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load result when load is called`() {
        runTest {
            // Arrange
            whenever(remoteDataSource.fetchCharacters(any()))
                .thenReturn(dataWrapperResponseFactory.create())

            // Act
            val result = charactersPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    null,
                    loadSize = 2,
                    false
                )
            )

            // Assert
            val expected = listOf(
                characterFactory.create(CharacterFactory.Hero.ThreeDMan),
                characterFactory.create(CharacterFactory.Hero.ABomb),
            )

            Assert.assertEquals(
                PagingSource.LoadResult.Page(
                    data = expected,
                    prevKey = null,
                    nextKey = 20
                ),
                result
            )
        }
    }

    @Test
    fun `should return a error load result when load is called`() {
        runTest {
            // Arrange
            val exception = RuntimeException()
            whenever(remoteDataSource.fetchCharacters(any()))
                .thenThrow(exception)

            // Act
            val result = charactersPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

            // Assert
            Assert.assertEquals(
                PagingSource.LoadResult.Error<Int, Character>(exception),
                result
            )
        }
    }
}
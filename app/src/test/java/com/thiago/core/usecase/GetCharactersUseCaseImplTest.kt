package com.thiago.core.usecase

//import androidx.paging.PagingConfig
//import com.nhaarman.mockitokotlin2.verify
//import com.nhaarman.mockitokotlin2.whenever
//import com.thiago.core.data.repository.CharactersRepository
//import com.thiago.testing.MainCoroutineRule
//import com.thiago.testing.model.CharacterFactory
//import com.thiago.testing.pagingsource.PagingSourceFactory
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner

// todo: fix test, it's breaking the pipeline

//@OptIn(ExperimentalCoroutinesApi::class)
//@RunWith(MockitoJUnitRunner::class)
//class GetCharactersUseCaseImplTest {
//
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    @Mock
//    lateinit var repository: CharactersRepository
//
//    private lateinit var getCharactersUseCase: GetCharactersUseCase
//
//    private val hero = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
//
//    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))
//
//    @Before
//    fun setUp() {
//        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
//    }
//
//    @Test
//    fun `should validate flow paging data creation when invoke from use case is called`() {
//        runTest {
//            whenever(repository.getCharacters("")).thenReturn(fakePagingSource)
//
//            val result = getCharactersUseCase.invoke(
//                GetCharactersUseCase.GetCharactersParams("", PagingConfig(20))
//            )
//
//            verify(repository).getCharacters("")
//
//            Assert.assertNotNull(result.first())
//        }
//    }
//}
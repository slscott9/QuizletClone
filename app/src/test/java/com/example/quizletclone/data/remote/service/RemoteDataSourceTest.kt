package com.example.quizletclone.data.remote.service

import com.example.CoroutineTestRule
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.apache.maven.settings.Server
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class RemoteDataSourceTest {

    //test subject using interface as reference to remoteDataSourceImpl
    private lateinit var remoteDataSource: RemoteDataSourceInterface

    private lateinit var quizletApi: QuizletApi
    private lateinit var serverResponse: ServerResponse

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {

        quizletApi = mock()
        serverResponse = ServerResponse(
            true,
            "Success"
        )

        remoteDataSource = RemoteDataSource(quizletApi)
    }

    @Test
    fun assert_register_function_returns_exception() = runBlocking<Unit>{
         val exception = Exception()

        doAnswer { throw Exception() }.`when`(quizletApi).register(any())

        val response = remoteDataSource.register(any())

        assertEquals(response, exception)


    }

}
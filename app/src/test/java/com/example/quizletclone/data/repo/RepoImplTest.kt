package com.example.quizletclone.data.repo

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.CoroutineTestRule
import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.example.quizletclone.other.Resource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


/*
    Since interfaces are used for repo, remoteDataSource, and localDataSource we can test the functions using the interfaces
    and mock object for the interfaces to return. We make remoteDataSource and localDataSource return mock objects
    and use these to test the repository's methods functionality

    Throw different things at the repos methods using mock objects returned from remote and local
    make assertions on the repo method's behavior,  based on local and remote methods behavior.

    based on what remote or local method the repo calls we use that method to test the repo's actions when calling that function with
    its return values.

 */
class RepoImplTest {
    lateinit var repository: RepoInterface

    //Collaborators
    lateinit var localDataSource : LocalDataSourceInterface
    private lateinit var remoteDataSource : RemoteDataSourceInterface
    private lateinit var resource: Resource<String>
    private lateinit var serverResponse: ServerResponse
    private lateinit var serverResponseError: ServerResponse
    private lateinit var context: Application


    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() = runBlocking{
        remoteDataSource = mock()
        localDataSource = mock()
        context = Application()
        resource = Resource.success("success")
        serverResponse = ServerResponse(true, "success")
        serverResponseError = ServerResponse(false, "failure")

        /*
            Every time these functions are called we return a server response

            unless we specify other wise
         */
        whenever(remoteDataSource.login(any())).thenReturn(serverResponse)
        whenever(remoteDataSource.register(any())).thenReturn(serverResponse)



        repository = RepoImpl(localDataSource, remoteDataSource, context)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun login_returns_sucess_resource() = runBlocking<Unit>{
        whenever(remoteDataSource.login(any())).thenReturn(serverResponse)

            repository.login("stuart", "scott", "slscot9")


        verify(remoteDataSource, times(1)).login(any())
    }

    @Test
    fun login_returns_resource_error() = runBlocking<Unit>{
        val exception = Exception()

        /*
            repository.login calls remoteDataSource.login

            remoteDataSource.login makes a safeApiRequest which throw exception if network call fails

            make remoteDataSource.login throw an exception.

            repo.login catches the exception and returns a resource.error
         */
        doAnswer { throw exception }.`when`(remoteDataSource).login(any())

            val response = repository.login("stuart", "scott", "slscott9")

        assertEquals(response, Resource.error(data = null, message = "Couldn't connect to server. Check internet connection"))


    }

    @Test
    fun repo_login_method_returns_resource_success() = runBlocking<Unit> {
        whenever(remoteDataSource.login(any())).thenReturn(serverResponse)

        val response = repository.login("stuart", "scott", "slscott9")

        assertEquals(response, Resource.success("success" ))
    }

    @Test
    fun repo_method_register_returns_resource_success() = runBlocking<Unit> {

        val resource = repository.register("stuart", "scott", "slscott9")

        //remoteDataSource.register returns a server response or an exception
        //based off of a server response or exception the repo will return a Resource.error or Resource.success

        //check if Resource<String> returned from repository.register() is equal to Resource.success
        assertEquals(resource, Resource.success("success"))
    }

    @Test
    fun repo_method_register_returns_resource_error() = runBlocking<Unit> {
        val exception = Exception()

        doAnswer { throw exception}.`when`(remoteDataSource).register(any())
        //exception is caught by repository which then returns a Resource.error

        val response = repository.register("stuart", "scott", "slscott9")

        assertEquals(response, Resource.error("Couldn't connect to server. Check internet connection", null))


    }





}
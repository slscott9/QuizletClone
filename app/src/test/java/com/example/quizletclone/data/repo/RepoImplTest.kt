package com.example.quizletclone.data.repo

import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Before

class RepoImplTest {
    lateinit var repoImpl: RepoImpl

    //Collaborators
    lateinit var localDataSource : LocalDataSourceInterface
    private lateinit var remoteDataSource : RemoteDataSourceInterface

    @Before
    fun setUp() {
        localDataSource = mock()
    }
}
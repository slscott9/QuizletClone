package com.example.quizletclone.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.getOrAwaitValue
import com.google.common.truth.Truth.assertAbout
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class QuizletDaoTest {

    private lateinit var database : QuizletDatabase
    private lateinit var dao : QuizletDao
    private lateinit var SyncedSet : Set
    private lateinit var unSyncedSet : Set
    private lateinit var unSyncedTerm: Term
    private lateinit var SynchedTerm: Term
    private lateinit var termList : List<Term>
    private lateinit var folder1 : Folder
    private lateinit var folder2 : Folder

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /*
        setup is called before the execution of EACH @Test function. We get new objects for each @Test function
     */
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuizletDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.dao()

        SyncedSet = Set(
            folderId = null,
            setId = 1L,
            setName = "new set",
            userEmail = "stuart@gmail.com",
            termCount = 1,
            isSynced = true
        )

        unSyncedSet = Set(
            folderId = null,
            setId = 2L,
            setName = "new set",
            userEmail = "stuart@gmail.com",
            termCount = 2,
            isSynced = false
        )

        termList = listOf<Term>(
            Term(
                setId = 2L,
                termId = 1,
                question = "what is 1",
                answer = "1",
                isSynced = false,
                userEmail = "newUser@gmail.com"
            ),

            Term(
                setId = 2L,
                termId = 2,
                question = "what is 2",
                answer = "2",
                isSynced = false,
                userEmail = "newUser@gmail.com"

            )
        )
        SynchedTerm = Term(
            setId = 1L,
            termId = 2,
            question = "what is 2",
            answer = "2",
            isSynced = false,
            userEmail = "newUser@gmail.com"

        )


        unSyncedTerm = Term(
            setId = 1L,
            termId = 2,
            question = "what is 2",
            answer = "2",
            isSynced = true,
            userEmail = "newUser@gmail.com"

        )

        folder1 = Folder(
            folderId = 1L,
            name = "folder1",
            userEmail = "folder1@email",
            userName = "folder username",
            description = "folder 1 description",
            isSynced = true
        )

        folder2 = Folder(
            folderId = 2L,
            name = "folder2",
            userEmail = "folder2@email",
            userName = "folder 2 username",
            description = "folder 2 description",
            isSynced = true
        )
    }

    /*
        @After function wil be executed immediately after each @Test function

        Idea behind @After is to clear resources or clear certain states
     */
    @After
    fun tearDoqn() {
        database.close()
    }

    @Test
    fun assert_that_insertSet_and_getSetWithId_returns_set() = runBlockingTest{


        dao.insertSet(SyncedSet)
        val setReturned = dao.getSetWithId(1)

        assertThat(setReturned).isEqualTo(SyncedSet)

    }

    @Test
    fun check_that_insertTerms_inserts_in_database() = runBlockingTest{

        dao.insertSet(SyncedSet)
        dao.insertTerms(termList)

        val returnedTermList = dao.getAllTermsWithSetId(1)
        assertThat(returnedTermList).isEqualTo(termList)
    }

    @Test
    fun check_if_dao_returns_set_with_term_object() = runBlockingTest {


        val setid = dao.insertSet(SyncedSet)


        dao.insertTerms(termList)

        val setWithTerm = dao.getSetsAndTermsWithId(setid).getOrAwaitValue()

        assertThat(setWithTerm.termList.size).isEqualTo(2)
        assertThat(setWithTerm.set.setName).isEqualTo("new set")
    }




    @Test
    fun check_if_get_sets_and_terms_returns_set_with_term_object() = runBlockingTest{


        dao.insertSet(SyncedSet)
        dao.insertTerms(termList)

        val setWithTerms = dao.getSetsAndTermsWithId(1).getOrAwaitValue()
        assertThat(setWithTerms.set.setId).isEqualTo(1)
        assertThat(setWithTerms.termList.size).isEqualTo(2)
    }

    @Test
    fun asset_that_getAlltermsWithId_returns_terms_with_id() = runBlockingTest{


        dao.insertSet(SyncedSet)
        dao.insertTerms(termList)
        val setList = dao.getAllTermsWithSetId(1)
        assertThat(setList.size).isEqualTo(2)


    }

    @Test
    fun assert_that_getAllSets_returns_sets_list() = runBlockingTest{

        dao.insertSet(SyncedSet)
        dao.insertSet(unSyncedSet)

        dao.getAllSets().take(1).collect {
            assertThat(it).isEqualTo(listOf(SyncedSet, unSyncedSet))
        }


    }

    @Test
    fun check_insertFolder_and_getAllFolders_insert_and_returns_folder_list() = runBlockingTest{


        dao.insertFolder(folder1)
        dao.insertFolder(folder2)

        dao.getAllFolders().take(1).collect {
            assertThat(it).isEqualTo(listOf(folder1, folder2))
        }
    }

    @Test
    fun check_if_getSetsWithSearchParam_returns_correct_sets() = runBlockingTest{

        dao.insertSet(SyncedSet)

        val setFromSearch = dao.getSetsWithSearchParam("new set")

        assertThat(setFromSearch[0]).isEqualTo(SyncedSet)
    }

    @Test
    fun check_that_getUnSyncedSets_returns_unSyncedSets() = runBlockingTest{

        dao.insertSet(unSyncedSet)
        dao.insertSet(SyncedSet)

        val unsynchedSets = dao.getUnSyncedSets(false)

        assertThat(unsynchedSets).isNotEmpty()
    }

    @Test fun check_that_getAllUnSyncedTermsWithTermId_returns_unSyncedTerms() = runBlockingTest {

        dao.insertSet(unSyncedSet)
        dao.insertTerms(termList)

        val unSyncedTerms = dao.getUnSyncedTermsWithSetId(false, 2)

        assertThat(unSyncedTerms.size).isEqualTo(2)
    }
}

package com.example.quizletclone.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.google.common.truth.Truth.assertThat
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuizletDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.dao()
    }

    @After
    fun tearDoqn() {
        database.close()
    }

    @Test
    fun check_if_dao_returns_set_with_term_object() = runBlockingTest {
        val set = Set(
            folderId = null,
            setName = "new set",
            userEmail = "stuart@gmail.com",
            termCount = 2,
            timeStamp = Date.from(Instant.now()).time
        )

        val setid = dao.insertSet(set)

        val termList = listOf<Term>(
            Term(
                setId = setid,
                question = "what is 1",
                answer = "1",
                timeStamp = Date.from(Instant.now()).time
            ),

            Term(
                setId = setid,
                question = "what is 2",
                answer = "2",
                timeStamp = Date.from(Instant.now()).time
            )
        )
        dao.insertTerms(termList)

        val setWithTerm = dao.getSetsAndTermsWithId(setid)

        assertThat(setWithTerm.termList.size).isEqualTo(2)
        assertThat(setWithTerm.set.setName).isEqualTo("new set")
    }
}

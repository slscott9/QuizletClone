package com.example.work

import android.content.Context
import android.content.SharedPreferences
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quizletclone.data.dto.add.asSetContainer
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import timber.log.Timber
import java.lang.Exception

class RefreshWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository : RepoInterface,
    private val sharedPreferences: SharedPreferences
) : CoroutineWorker(context, workerParameters){

    companion object {
        const val REFRESH_WORKER = "REFRESH_WORKER"
    }


    override suspend fun doWork(): Result {

        Timber.i("Starting work")
        try {

            val userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)

            val unSyncedSetsWithTerms =  ArrayList<SetWithTerms>()


            val unSyncedSets = repository.getUnSyncedSets(false)

            unSyncedSets.forEach { set ->
                val unSyncedTerms = repository.getUnSyncedTermsWithSetId(false, set.setId)

                unSyncedSetsWithTerms.add(
                    SetWithTerms(
                        set,
                        termList = unSyncedTerms
                    )
                )
            }

            val addSetContainer = unSyncedSetsWithTerms.asSetContainer()

            repository.sendSetsToNetwork(addSetContainer, userEmail ?: "")






        }catch (e : Exception){
            return Result.retry()
        }
        return Result.success()
    }
}
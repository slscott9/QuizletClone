package com.example.work

import android.content.Context
import android.content.SharedPreferences
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quizletclone.data.dto.add.asSetContainer
import com.example.quizletclone.data.dto.asSetWithTerms
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.remote.BasicAuthInterceptor
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import okhttp3.Credentials
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class RefreshWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository : RepoInterface,
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val sharedPreferences: SharedPreferences
) : CoroutineWorker(context, workerParameters){



    companion object {
        const val REFRESH_WORKER = "REFRESH_WORKER"
    }

    private fun authenticateAPI(userName: String, password: String) {
        basicAuthInterceptor.userName = userName
        basicAuthInterceptor.password = password
    }


    override suspend fun doWork(): Result {

        Timber.i("Starting work")
        try {



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

            Timber.i(sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL))
            repository.sendSetsToNetwork(addSetContainer, sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL) ?: "")



            /*
                Delete all unsynced

                replace with user data
             */

            repository.deleteAllFolders()
            repository.deleteAllSets()

            val userData = repository.getUserData(sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL) ?: "")

            val sets = userData.userSets.asSetWithTerms()

            repository.insertSetWithTerms(sets)


            /*
                Term are not being sent to network
             */





        }catch (e : Exception){
            return Result.retry()
        }
        return Result.success()
    }
}
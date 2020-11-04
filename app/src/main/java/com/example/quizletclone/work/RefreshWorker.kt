package com.example.quizletclone.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RefreshWorker @WorkerInject constructor(
    @Assisted context : Context,
    @Assisted workerParameters: WorkerParameters,

) : CoroutineWorker(context, workerParameters){

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}
package com.example.quizletclone.data.remote

import android.accounts.NetworkErrorException
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import com.example.quizletclone.utils.ApiException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.Http2
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.HTTP
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        }
        else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message")) //target the "message" json string from response from dad append it to message variable
                } catch (e: JSONException) {

                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }














}
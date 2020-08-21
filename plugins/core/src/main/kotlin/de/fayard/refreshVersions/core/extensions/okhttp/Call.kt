package de.fayard.refreshVersions.core.extensions.okhttp

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal suspend fun Call.await(): Response {
    try {
        return suspendCancellableCoroutine { c ->
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    c.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    c.resume(response)
                }
            })
        }
    } catch (t: Throwable) {
        cancel()
        throw t
    }
}

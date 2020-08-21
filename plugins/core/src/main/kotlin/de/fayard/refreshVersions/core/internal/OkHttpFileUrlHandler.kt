package de.fayard.refreshVersions.core.internal

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.FileNotFoundException
import java.net.URL

internal object OkHttpFileUrlHandler : Interceptor {

    fun rewrittenIfFileSchemeUrl(url: String): String {
        return if (url.startsWith("file:")) {
            url.replaceFirst("file:", fileSystemMarkerPrefix)
        } else url
    }

    private const val fileSystemMarkerHost = "filesystem.local"
    private const val fileSystemMarkerPrefix = "http://$fileSystemMarkerHost"

    private fun restoreFileUrl(markedFakeHttpUrl: String): String {
        return markedFakeHttpUrl.replaceFirst(fileSystemMarkerPrefix, "file:")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        if (url.host != fileSystemMarkerHost) return chain.proceed(request)
        val fileUrl = restoreFileUrl(url.toString())
        return try {
            Response.Builder()
                .body(URL(fileUrl).readBytes().toResponseBody())
                .code(200)
                .message("Some file")
                .protocol(Protocol.HTTP_1_0)
                .request(request)
                .build()
        } catch (e: FileNotFoundException) {
            Response.Builder()
                .body("".toResponseBody())
                .code(404)
                .message(e.message ?: "File not found ($fileUrl)")
                .protocol(Protocol.HTTP_1_0)
                .request(request)
                .build()
        }
    }
}

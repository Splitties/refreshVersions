package dependencies

/**
 * The actual dependency version comes from `gradle.properties`
 * from either `version.$NAME=xxx` or `version.com.squareup.okhttp=xxx` or `version.com.squreup.retrofit2=xxxx`
 **/
object Square {

    const val okHttp = "com.squareup.okhttp3:okhttp:$placeholderVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$placeholderVersion"
    const val okhttpMockWebserver = "com.squareup.okhttp3:mockwebserver:$placeholderVersion"

    private const val artifact = "com.squareup.retrofit2"
    const val retrofit = "$artifact:retrofit:$placeholderVersion"
    const val retrofitMoshi = "$artifact:converter-moshi:$placeholderVersion"
    const val retrofitGson = "$artifact:converter-gson:$placeholderVersion"
    const val retrofitJava8 = "$artifact:adapter-java8:$placeholderVersion"
    const val retrofitMock = "$artifact:retrofit-mock:$placeholderVersion"
    const val retrofitSimplexml = "$artifact:converter-simplexml:$placeholderVersion"
    const val retrofitScalars = "$artifact:converter-scalars:$placeholderVersion"
    const val retrofitJackson = "$artifact:converter-jackson:$placeholderVersion"
    const val retrofitRxjava1 = "$artifact:adapter-rxjava:$placeholderVersion"
    const val retrofitRxjava2 = "$artifact:adapter-rxjava2:$placeholderVersion"

    const val moshi = "com.squareup.moshi:moshi:$placeholderVersion"
}

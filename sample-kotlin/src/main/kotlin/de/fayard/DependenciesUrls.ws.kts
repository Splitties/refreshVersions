package de.fayard

fun urlOfDependency(repoUrl: String, mavenCoordinates: String): String {
    val url = repoUrl.let { url ->
        if (url.endsWith('/')) url else "$url/"
    }.removePrefix("http://").removePrefix("https://")
    val group = mavenCoordinates.substringBefore(':')
    val name = mavenCoordinates.substringAfter(':').substringBefore(':')
    return "https://$url${group.replace('.', '/')}/$name"
}

fun pomFileUrl(repoUrl: String, mavenCoordinates: String): String {
    val version = mavenCoordinates.substringAfterLast(':')
    val name = mavenCoordinates.substringAfter(':').substringBefore(':')
    val fileName = "$name-$version.pom"
    return urlOfDependency(repoUrl, mavenCoordinates) + "/$version/" + fileName
}

arrayOf(
    "com.google.firebase:firebase-bom:24.6.0",
    "com.google.firebase:firebase-ml-natural-language-translate-model:20.0.7"
).forEach { mavenCoordinates ->
    println(
        pomFileUrl(
            repoUrl = "maven.google.com/",
            mavenCoordinates = mavenCoordinates
        )
    )
}

arrayOf(
    "org.jetbrains.kotlinx:kotlinx-coroutines-core"
).forEach { mavenCoordinates ->
    println(
        urlOfDependency(
            repoUrl = "https://repo1.maven.org/maven2/",
            mavenCoordinates = mavenCoordinates
        )
    )
}

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation(Square.okHttp3.okHttp)
    implementation(KotlinX.collections.immutable)
}

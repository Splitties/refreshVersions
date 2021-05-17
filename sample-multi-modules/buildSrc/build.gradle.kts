plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation(Square.okHttp3.okHttp)
    implementation(KotlinX.collections.immutable)
}

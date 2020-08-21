package testutils

fun isInCi(): Boolean = System.getenv("CI") == "true"

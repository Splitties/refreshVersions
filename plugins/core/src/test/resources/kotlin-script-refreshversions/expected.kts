#!/usr/bin/env kotlin

@file:Repository("https://jcenter.bintray.com")
@file:Repository("https://jitpack.io")
@file:DependsOn("io.rsocket.kotlin:rsocket-core-jvm:0.13.0-SNAPSHOT")
////                                   # available:0.15.0")
////                                   # available:0.16.0")
@file:DependsOn("io.ktor:ktor-client-okhttp:1.5.2")
////                           # available:1.6.0")
////                           # available:1.7.0")
@file:DependsOn("com.squareup.wire:wire-moshi-adapter:3.4.0")
@file:CompilerOptions("-jvm-target", "17")

import org.jetbrains.kotlin.script.util.CompilerOptions
import org.jetbrains.kotlin.script.util.DependsOn
import org.jetbrains.kotlin.script.util.Repository

fun main() {
    println("Hello World")
}
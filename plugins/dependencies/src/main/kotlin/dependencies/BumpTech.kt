@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object BumpTech : IsNotADependency {

    /**
     * Glide is a fast and efficient open source media management and image loading framework for Android. Glide offers an easy to use API,
     * a performant and extensible resource decoding pipeline and automatic resource pooling.
     *
     * [Official website](https://bumptech.github.io/glide)
     *
     * GitHub page: [bumptech/glide](https://github.com/bumptech/glide)
     */
    val glide = Glide

    object Glide : DependencyNotationAndGroup(group = "com.github.bumptech.glide", name = "glide") {

        /** A set of annotations for configuring Glide. */
        val annotations = module("annotations")

        /** An integration library to support AVIF images in Glide. */
        val avif = module("avif-integration")

        /** Glide's anntation processor. */
        val compiler = module("compiler")

        /** An integration library to integrate with Jetpack Compose. */
        val compose = module("compose")

        /** An integration library for using Glide with [`ListenableFutures`](https://developer.android.com/guide/background/listenablefuture). */
        val concurrent = module("concurrent-integration")

        /** An integration library to use Cronet to fetch data over HTTP/HTTPS in Glide.  */
        val cronet = module("cronet-integration")

        /** A cache that uses a bounded amount of space on a filesystem. Based on Jake Wharton's tailored for Glide. */
        val diskLruCache = module("disklrucache")

        /** An integration library allowing users to re-encode or create animated GIFs. */
        val gifEncoder = module("gifencoder-integration")

        /** Implementation of GifDecoder that is more memory efficient to animate for Android devices.  */
        val gifDecoder = module("gifdecoder")

        /** Glide's KSP based annotation processor. */
        val ksp = module("ksp")

        /** An integration library to improve Kotlin interop with Glide. */
        val ktx = module("ktx")

        /** A set of mocks to ease testing with Glide. */
        val mocks = module("mocks")

        /** An integration library to use OkHttp 2.x to fetch data over HTTP/HTTPS in Glide. */
        val okhttp = module("okhttp-integration")

        /** An integration library to use OkHttp 3.x to fetch data over HTTP/HTTPS in Glide. */
        val okhttp3 = module("okhttp3-integration")

        /** An integration library to display images in `RecyclerView`. */
        val recyclerView = module("recyclerView-integration")

        /** An integration library to use Volley to fetch data over HTTP/HTTPS in Glide. */
        val volley = module("concurrent-integration")
    }
}

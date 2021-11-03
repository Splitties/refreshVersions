import de.fayard.refreshVersions.core.DependencyNotation
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm")
}

dependencies {
    //FIXME: Replace with the new dependency and remove these comments.
    implementation("androidx.wear:wear-watchface-client:_")
//           moved:"androidx.wear.watchface:watchface-client:_")
    //FIXME: Replace with the new dependency and remove these comments.
    implementation(DependencyNotation.parse("androidx.wear:wear-watchface-client").withoutVersion())
//           moved:DependencyNotation.parse("androidx.wear.watchface:watchface-client").withoutVersion())
    //FIXME: Replace with the new dependency and remove these comments.
    implementation(DependencyNotation.parse("androidx.wear:wear-watchface-client").withVersion("AndroidX.wear.watchFace.client"))
//           moved:DependencyNotation.parse("androidx.wear.watchface:watchface-client").withVersion("AndroidX.wear.watchFace.client"))
    //FIXME: Replace with the new dependency and remove these comments.
    implementation("androidx.wear:wear-watchface-client:_")
//           moved:"androidx.wear.watchface:watchface-client:_")
    implementation("AndroidX.wear.watchFace.client")
    implementation("com.somegroup:somegroup-something:_")
    //FIXME: Remove dependency now that somegroup one has been deprecated.
    implementation("com.somegroup:somegroup-one:_")
    implementation ( "com.somegroup:somegroup-two:_" )
//             moved:AnotherGroup.two )
    implementation (" SomeGroup.two ")
}

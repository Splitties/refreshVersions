package de.fayard.refreshVersions.core.internal

import org.gradle.api.Transformer
import org.gradle.api.internal.provider.Providers
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import java.util.function.BiFunction

@InternalRefreshVersionsApi
@Suppress("UnstableApiUsage")
interface StandaloneProvider<T> : Provider<T>, ProviderConvertible<T> {

    val value: T

    override fun asProvider(): Provider<T> = Providers.of(value)

    override fun get(): T = asProvider().get()

    @Suppress("UsePropertyAccessSyntax")
    override fun getOrNull() = asProvider().getOrNull()

    override fun getOrElse(defaultValue: T): T = asProvider().getOrElse(defaultValue)

    override fun <S : Any?> map(
        transformer: Transformer<out S, in T>
    ): Provider<S> = asProvider().map(transformer)

    override fun <S : Any?> flatMap(
        transformer: Transformer<out Provider<out S>, in T>
    ): Provider<S> = asProvider().flatMap(transformer)

    override fun isPresent() = asProvider().isPresent

    override fun orElse(value: T): Provider<T> = asProvider().orElse(value)

    override fun orElse(provider: Provider<out T>): Provider<T> = asProvider().orElse(provider)

    override fun forUseAtConfigurationTime(): Provider<T> = asProvider().forUseAtConfigurationTime()

    override fun <B : Any?, R : Any?> zip(
        right: Provider<B>,
        combiner: BiFunction<T, B, R>
    ): Provider<R> = asProvider().zip(right, combiner)
}

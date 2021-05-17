package de.fayard.refreshVersions.core.internal

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class ResettableDelegates {

    fun reset() {
        associatedDelegates.forEach {
            with(it) { with(null) { reset() } }
        }
    }

    inner class NullableDelegate<T> : Delegate<T?>() {

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? = field

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            field = value
        }
    }

    inner class LateInit<T : Any> : Delegate<T>() {

        val isInitialized: Boolean get() = this.field != null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T = field ?: error(
            "Property ${property.name} not initialized yet! " +
                    "Has it been used after reset or before init?"
        )

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            field = value
        }
    }

    inner class Lazy<T : Any>(private val initializer: () -> T) : Delegate<T>() {

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return field ?: initializer().apply { field = this }
        }
    }

    inner class MutableLazy<T : Any>(private val initializer: () -> T) : Delegate<T>() {

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return field ?: initializer().apply { field = this }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            field = value
        }
    }

    abstract inner class Delegate<T> : ReadOnlyProperty<Any?, T> {

        @Suppress("unused") // Discourage use outside this file.
        fun Nothing?.reset() {
            field = null
        }

        @JvmField protected var field: T? = null

        init {
            @Suppress("LeakingThis") // Safe in our case where the references are kept private.
            associatedDelegates.add(this)
        }
    }

    private val associatedDelegates = mutableListOf<Delegate<*>>()
}

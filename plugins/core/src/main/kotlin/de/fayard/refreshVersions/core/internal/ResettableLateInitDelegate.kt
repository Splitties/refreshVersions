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

        override fun Nothing?.reset() {
            field = null
        }

        private var field: T? = null

        init {
            associatedDelegates.add(this)
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

        override fun Nothing?.reset() {
            field = null
        }

        private var field: T? = null

        init {
            associatedDelegates.add(this)
        }
    }

    inner class Lazy<T : Any>(private val initializer: () -> T) : Delegate<T>() {

        private var value: T? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value ?: initializer().apply { value = this }
        }

        override fun Nothing?.reset() {
            value = null
        }
    }

    abstract class Delegate<T> : ReadOnlyProperty<Any?, T> {
        abstract fun Nothing?.reset()
    }

    private val associatedDelegates = mutableListOf<Delegate<*>>()
}

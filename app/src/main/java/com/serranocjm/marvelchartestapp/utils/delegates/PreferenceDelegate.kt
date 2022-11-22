package com.serranocjm.marvelchartestapp.utils.delegates

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate class file to handle shared preferences
 */
abstract class PreferenceDelegate<T> : ReadWriteProperty<Any, T>, KoinComponent {

    companion object {
        private const val FIRST_TIME_DIALOG = "FIRST_TIME_DIALOG"
        var firstTimeDialog by booleanPreferenceDelegate(FIRST_TIME_DIALOG)
    }

    protected val sharedPreferences: SharedPreferences by inject()
}

class floatPreferenceDelegate(
    private val key: String,
    private val defaultValue: Float = 0.toFloat()
) : PreferenceDelegate<Float>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getFloat(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) =
        sharedPreferences.edit { putFloat(key, value) }
}

class booleanPreferenceDelegate(
    private val key: String,
    private val defaultValue: Boolean = false
) : PreferenceDelegate<Boolean>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getBoolean(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) =
        sharedPreferences.edit { putBoolean(key, value) }
}

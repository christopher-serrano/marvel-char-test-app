package com.serranocjm.marvelchartestapp.utils.general

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.serranocjm.marvelchartestapp.utils.custom.OnOneOffClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.BuildConfig
import java.lang.reflect.Type
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

/**
 * Custom extensions file
 */

// General extensions

fun getClassName(anyClass: Any): String? {
    return anyClass::class.java.simpleName
}

inline fun <T> justTry(block: () -> T) = try {
    block()
} catch (_: Throwable) {
}

inline fun debugMode(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

// Object parsing-related extensions

inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}

inline fun <reified T> parseArray(json: String, typeToken: Type): T {
    val gson = GsonBuilder().create()
    return gson.fromJson<T>(json, typeToken)
}

fun Any?.toJsonString(): String? {
    return this?.let {
        val gson = Gson()
        gson.toJson(it)
    }
}

// Context-related extensions

fun Context.toastLong(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.toastShort(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

// View-related extensions

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.setOpacity(percent: Int) {
    this.background.alpha = percent
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isViewGone() = visibility == View.GONE

fun View.makeVisibleAlpha(duration: Long, endAction: () -> Unit = {}) {
    if (!isVisible()) makeVisible()
    if (alpha != 0f) alpha = 0f
    animate().alpha(1f).setDuration(duration).setInterpolator(AccelerateInterpolator())
        .withEndAction {
            endAction.invoke()
        }
}

fun View.makeInvisibleAlpha(duration: Long, endAction: () -> Unit = {}) {
    animate().alpha(0f).setDuration(duration).setInterpolator(AccelerateInterpolator())
        .withEndAction {
            endAction.invoke()
        }
}

fun View.makeGoneAlpha(duration: Long, endAction: () -> Unit = {}) {
    animate().alpha(0f).setDuration(duration).setInterpolator(AccelerateInterpolator())
        .withEndAction {
            endAction.invoke()
            makeGone()
        }
}

fun View.setOneOffClickListener(action: () -> Unit) {
    setOnClickListener(object : OnOneOffClickListener() {
        override fun onSingleClick(v: View?) {
            action.invoke()
        }
    })
}

// Other extensions

fun delayAction(delay: Long, action: () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
    kotlinx.coroutines.delay(delay)
    action.invoke()
}

fun String.toMd5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray(UTF_8))
fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

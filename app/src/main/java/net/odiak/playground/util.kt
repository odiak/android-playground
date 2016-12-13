package net.odiak.playground

import android.content.Context

inline fun <T, S> Iterable<T>.forEachWithObject(obj: S, f: (T, S) -> Unit): S = run {
    forEach { f(it, obj) }
    obj
}

fun Context.dp2px(dp: Int): Float =
        resources.displayMetrics.density * dp

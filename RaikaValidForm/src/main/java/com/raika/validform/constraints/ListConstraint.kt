package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun <T> Collection<T>?.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this != null) { errorListener?.invoke() }
}

fun <T> Collection<T>.isRequire(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.isNotEmpty()) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeAtMost(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size <= max) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeLessThan(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size < max) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeAtLeast(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size >= min) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeGreaterThan(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size >= min) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeIn(min: Int, max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size in min..max) { errorListener?.invoke() }
}

fun <T> Collection<T>.isSizeEqual(size: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.size == size) { errorListener?.invoke() }
}
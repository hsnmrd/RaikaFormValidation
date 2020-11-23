package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun <T> Collection<T>?.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this != null) { errorListener() }
}

fun <T> Collection<T>.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.isNotEmpty()) { errorListener() }
}

fun <T> Collection<T>.isSizeAtMost(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size <= max) { errorListener() }
}

fun <T> Collection<T>.isSizeLessThan(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size < max) { errorListener() }
}

fun <T> Collection<T>.isSizeAtLeast(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size >= min) { errorListener() }
}

fun <T> Collection<T>.isSizeGreaterThan(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size >= min) { errorListener() }
}

fun <T> Collection<T>.isSizeIn(min: Int, max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size in min..max) { errorListener() }
}

fun <T> Collection<T>.isSizeEqual(size: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.size == size) { errorListener() }
}
package com.form.raika.constraints

import com.form.raika.checkConstraintResult

fun Int?.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this != null) { errorListener() }
}

fun Int.isAtMost(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this <= max) { errorListener() }
}

fun Int.isLessThan(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this < max) { errorListener() }
}

fun Int.isAtLeast(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Int.isGreaterThan(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Int.isIn(min: Int, max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this in min..max) { errorListener() }
}

fun Int.isEqual(value: Int, errorListener: () -> Unit) {
    checkConstraintResult(this == value) { errorListener() }
}

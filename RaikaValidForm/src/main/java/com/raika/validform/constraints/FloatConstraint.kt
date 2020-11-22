package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun Float?.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this != null) { errorListener() }
}

fun Float.isAtMost(max: Float, errorListener: () -> Unit) {
    checkConstraintResult(this <= max) { errorListener() }
}

fun Float.isLessThan(max: Float, errorListener: () -> Unit) {
    checkConstraintResult(this < max) { errorListener() }
}

fun Float.isAtLeast(min: Float, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Float.isGreaterThan(min: Float, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Float.isIn(min: Float, max: Float, errorListener: () -> Unit) {
    checkConstraintResult(this in min..max) { errorListener() }
}

fun Float.isEqual(value: Float, errorListener: () -> Unit) {
    checkConstraintResult(this == value) { errorListener() }
}

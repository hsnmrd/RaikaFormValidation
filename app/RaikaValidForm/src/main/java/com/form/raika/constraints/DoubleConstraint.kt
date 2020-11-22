package com.form.raika.constraints

import com.form.raika.checkConstraintResult

fun Double?.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this != null) { errorListener() }
}

fun Double.isAtMost(max: Double, errorListener: () -> Unit) {
    checkConstraintResult(this <= max) { errorListener() }
}

fun Double.isLessThan(max: Double, errorListener: () -> Unit) {
    checkConstraintResult(this < max) { errorListener() }
}

fun Double.isAtLeast(min: Double, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Double.isGreaterThan(min: Double, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Double.isIn(min: Double, max: Double, errorListener: () -> Unit) {
    checkConstraintResult(this in min..max) { errorListener() }
}

fun Double.isEqual(value: Double, errorListener: () -> Unit) {
    checkConstraintResult(this == value) { errorListener() }
}

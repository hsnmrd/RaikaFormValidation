package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun Double?.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this != null) { errorListener?.invoke() }
}

fun Double.isAtMost(max: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this <= max) { errorListener?.invoke() }
}

fun Double.isLessThan(max: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this < max) { errorListener?.invoke() }
}

fun Double.isAtLeast(min: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Double.isGreaterThan(min: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Double.isIn(min: Double, max: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this in min..max) { errorListener?.invoke() }
}

fun Double.isEqual(value: Double, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this == value) { errorListener?.invoke() }
}

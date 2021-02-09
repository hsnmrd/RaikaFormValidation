package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun Float?.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this != null) { errorListener?.invoke() }
}

fun Float.isAtMost(max: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this <= max) { errorListener?.invoke() }
}

fun Float.isLessThan(max: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this < max) { errorListener?.invoke() }
}

fun Float.isAtLeast(min: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Float.isGreaterThan(min: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.let { it() } }
}

fun Float.isIn(min: Float, max: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this in min..max) { errorListener?.invoke() }
}

fun Float.isEqual(value: Float, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this == value) { errorListener?.invoke() }
}

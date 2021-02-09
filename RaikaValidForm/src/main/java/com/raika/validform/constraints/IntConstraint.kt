package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun Int?.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this != null) { errorListener?.invoke() }
}

fun Int.isAtMost(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this <= max) { errorListener?.invoke() }
}

fun Int.isLessThan(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this < max) { errorListener?.invoke() }
}

fun Int.isAtLeast(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Int.isGreaterThan(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Int.isIn(min: Int, max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this in min..max) { errorListener?.invoke() }
}

fun Int.isEqual(value: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this == value) { errorListener?.invoke() }
}

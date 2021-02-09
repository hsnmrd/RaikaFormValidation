package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult
import java.util.*

fun Date?.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this != null) { errorListener?.invoke() }
}

fun Date.isAtMost(max: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this <= max) { errorListener?.invoke() }
}

fun Date.isLessThan(max: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this < max) { errorListener?.invoke() }
}

fun Date.isAtLeast(min: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Date.isGreaterThan(min: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= min) { errorListener?.invoke() }
}

fun Date.isIn(min: Date, max: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this in min..max) { errorListener?.invoke() }
}

fun Date.isAtMost(max: Long, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this <= Date(max)) { errorListener?.invoke() }
}

fun Date.isLessThan(max: Long, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this < Date(max)) { errorListener?.invoke() }
}

fun Date.isAtLeast(min: Long, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= Date(min)) { errorListener?.invoke() }
}

fun Date.isGreaterThan(min: Long, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this >= Date(min)) { errorListener?.invoke() }
}

fun Date.isIn(min: Long, max: Long, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this in Date(min)..Date(max)) { errorListener?.invoke() }
}

fun Date.isEqual(date: Date, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this == date) { errorListener?.invoke() }
}
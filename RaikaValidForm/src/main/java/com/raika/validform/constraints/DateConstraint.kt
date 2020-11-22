package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult
import java.util.*

fun Date.isAtMost(max: Date, errorListener: () -> Unit) {
    checkConstraintResult(this <= max) { errorListener() }
}

fun Date.isLessThan(max: Date, errorListener: () -> Unit) {
    checkConstraintResult(this < max) { errorListener() }
}

fun Date.isAtLeast(min: Date, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Date.isGreaterThan(min: Date, errorListener: () -> Unit) {
    checkConstraintResult(this >= min) { errorListener() }
}

fun Date.isIn(min: Date, max: Date, errorListener: () -> Unit) {
    checkConstraintResult(this in min..max) { errorListener() }
}

fun Date.isAtMost(max: Long, errorListener: () -> Unit) {
    checkConstraintResult(this <= Date(max)) { errorListener() }
}

fun Date.isLessThan(max: Long, errorListener: () -> Unit) {
    checkConstraintResult(this < Date(max)) { errorListener() }
}

fun Date.isAtLeast(min: Long, errorListener: () -> Unit) {
    checkConstraintResult(this >= Date(min)) { errorListener() }
}

fun Date.isGreaterThan(min: Long, errorListener: () -> Unit) {
    checkConstraintResult(this >= Date(min)) { errorListener() }
}

fun Date.isIn(min: Long, max: Long, errorListener: () -> Unit) {
    checkConstraintResult(this in Date(min)..Date(max)) { errorListener() }
}
package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isNotNull(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.isNotEmpty() || this.trim() == "null") { errorListener?.invoke() }
}

fun String.isRequire(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().isNotEmpty()) { errorListener?.invoke() }
}

fun String.isEmail(errorListener: (() -> Unit)? = null) {
    if (this.trim().isNotEmpty()) {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(this.trim())
        checkConstraintResult(matcher.matches()) { errorListener?.invoke() }
    }
}

fun String.isLengthAtMost(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length <= max) { errorListener?.invoke() }
}

fun String.isLengthLessThan(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length < max) { errorListener?.invoke() }
}

fun String.isLengthAtLeast(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length >= min) { errorListener?.invoke() }
}

fun String.isLengthGreaterThan(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length >= min) { errorListener?.invoke() }
}

fun String.isLengthIn(min: Int, max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length in min..max) { errorListener?.invoke() }
}

fun String.isLengthEqual(length: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().length == length) { errorListener?.invoke() }
}

fun String.isContainingNumber(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().contains(Regex(".*\\d.*"))) { errorListener?.invoke() }
}


fun String.isContainingUpperCase(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.trim().contains(Regex(".*[A-Z]"))) { errorListener?.invoke() }
}

fun String.isContaining(vararg value: String, errorListener: (() -> Unit)? = null) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.trim().contains(it)
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener?.invoke() }
}

fun String.isEqual(vararg value: String, errorListener: (() -> Unit)? = null) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.trim() == it.trim()
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener?.invoke() }
}
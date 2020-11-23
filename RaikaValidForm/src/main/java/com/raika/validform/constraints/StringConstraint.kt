package com.raika.validform.constraints

import android.widget.TextView
import com.raika.validform.checkConstraintResult
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this.isNotEmpty() || this.trim() == "null") { errorListener() }
}

fun String.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.trim().isNotEmpty()) { errorListener() }
}

fun String.isEmail(errorListener: () -> Unit) {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this.trim())
    checkConstraintResult(matcher.matches()) { errorListener() }
}

fun String.isLengthAtMost(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length <= max) { errorListener() }
}

fun String.isLengthLessThan(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length < max) { errorListener() }
}

fun String.isLengthAtLeast(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length >= min) { errorListener() }
}

fun String.isLengthGreaterThan(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length >= min) { errorListener() }
}

fun String.isLengthIn(min: Int, max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length in min..max) { errorListener() }
}

fun String.isLengthEqual(length: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.trim().length == length) { errorListener() }
}

fun String.isContainingNumber(errorListener: () -> Unit) {
    checkConstraintResult(this.trim().contains(Regex(".*\\d.*"))) { errorListener() }
}


fun String.isContainingUpperCase(errorListener: () -> Unit) {
    checkConstraintResult(this.trim().contains(Regex(".*[A-Z]"))) { errorListener() }
}

fun String.isContaining(vararg value: String, errorListener: () -> Unit) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.trim().contains(it)
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener() }
}

fun String.isEqual(vararg value: String, errorListener: () -> Unit) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.trim() == it.trim()
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener() }
}
package com.raika.validform.constraints

import android.widget.TextView
import com.raika.validform.checkConstraintResult
import java.util.regex.Matcher
import java.util.regex.Pattern

fun TextView.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim()
        .isNotEmpty()) { errorListener() }
}

fun TextView.isEmail(errorListener: () -> Unit) {
    if (this.text.trim().isNotEmpty()) {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(this.text.trim())
        checkConstraintResult(matcher.matches()) { errorListener() }
    }
}

fun TextView.isLengthAtMost(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length <= max) { errorListener() }
}

fun TextView.isLengthLessThan(max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length < max) { errorListener() }
}

fun TextView.isLengthAtLeast(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length >= min) { errorListener() }
}

fun TextView.isLengthGreaterThan(min: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length >= min) { errorListener() }
}

fun TextView.isLengthIn(min: Int, max: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length in min..max) { errorListener() }
}

fun TextView.isLengthEqual(length: Int, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString()
        .trim().length == length) { errorListener() }
}

fun TextView.isContainingNumber(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim().contains(Regex(".*\\d.*"))) { errorListener() }
}


fun TextView.isContainingUpperCase(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim().contains(Regex(".*[A-Z]"))) { errorListener() }
}

fun TextView.isContaining(vararg value: String, errorListener: () -> Unit) {
    var isOneItemContaining = false
    run breaker@{
        value.forEach {
            isOneItemContaining = this.text.toString().trim().contains(it)
            if (isOneItemContaining) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemContaining) { errorListener() }
}

fun TextView.isEqual(vararg value: String, errorListener: () -> Unit) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.text.toString().trim() == it.trim()
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener() }
}

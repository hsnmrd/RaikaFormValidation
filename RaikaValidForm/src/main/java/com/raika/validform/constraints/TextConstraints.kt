package com.raika.validform.constraints

import android.widget.TextView
import com.raika.validform.checkConstraintResult
import java.util.regex.Matcher
import java.util.regex.Pattern

fun TextView.isRequire(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString().trim()
        .isNotEmpty()) { errorListener?.invoke() }
}

fun TextView.isEmail(errorListener: (() -> Unit)? = null) {
    if (this.text.trim().isNotEmpty()) {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(this.text.trim())
        checkConstraintResult(matcher.matches()) { errorListener?.invoke() }
    }
}

fun TextView.isLengthAtMost(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length <= max) { errorListener?.invoke() }
}

fun TextView.isLengthLessThan(max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length < max) { errorListener?.invoke() }
}

fun TextView.isLengthAtLeast(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length >= min) { errorListener?.invoke() }
}

fun TextView.isLengthGreaterThan(min: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length >= min) { errorListener?.invoke() }
}

fun TextView.isLengthIn(min: Int, max: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length in min..max) { errorListener?.invoke() }
}

fun TextView.isLengthEqual(length: Int, errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString()
        .trim().length == length) { errorListener?.invoke() }
}

fun TextView.isContainingNumber(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString().trim().contains(Regex(".*\\d.*"))) { errorListener?.invoke() }
}


fun TextView.isContainingUpperCase(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.text.toString().trim().contains(Regex(".*[A-Z]"))) { errorListener?.invoke() }
}

fun TextView.isContaining(vararg value: String, errorListener: (() -> Unit)? = null) {
    var isOneItemContaining = false
    run breaker@{
        value.forEach {
            isOneItemContaining = this.text.toString().trim().contains(it)
            if (isOneItemContaining) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemContaining) { errorListener?.invoke() }
}

fun TextView.isEqual(vararg value: String, errorListener: (() -> Unit)? = null) {
    var isOneItemEqual = false
    run breaker@{
        value.forEach {
            isOneItemEqual = this.text.toString().trim() == it.trim()
            if (isOneItemEqual) {
                return@breaker
            }
        }
    }
    checkConstraintResult(isOneItemEqual) { errorListener?.invoke() }
}

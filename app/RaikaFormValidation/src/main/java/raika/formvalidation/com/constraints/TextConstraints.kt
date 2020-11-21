package raika.formvalidation.com.constraints

import android.widget.TextView
import raika.formvalidation.com.checkConstraintResult
import java.util.regex.Matcher
import java.util.regex.Pattern

fun TextView.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim()
        .isNotEmpty()) { errorListener() }
}

fun TextView.isEmail(errorListener: () -> Unit) {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this.text.trim())
    checkConstraintResult(matcher.matches()) { errorListener() }
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

fun TextView.isContaining(value: String, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim()
        .contains(value)) { errorListener() }
}

fun TextView.isConfirm(value: String, errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim() == value) { errorListener() }
}

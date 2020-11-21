package raika.raikaformvalidation.com

import android.widget.EditText
import java.util.regex.Matcher
import java.util.regex.Pattern

fun EditText.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim().isNotEmpty()) { errorListener() }
}

fun EditText.isEmailValid(errorListener: () -> Unit) {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern =
        Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this.text.trim())
    checkConstraintResult(matcher.matches()) { errorListener() }
}

fun EditText.withMaxCharacter(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim().isNotEmpty()) { errorListener() }
}

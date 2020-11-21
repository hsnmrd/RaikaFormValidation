package raika.formvalidation.com.constraints

import raika.formvalidation.com.checkConstraintResult

fun Int?.isNotNull(errorListener: () -> Unit) {
    checkConstraintResult(this != null) { errorListener() }
}
package raika.formvalidation.com.constraints

import raika.formvalidation.com.checkConstraintResult

fun <T> Collection<T>.isRequire (errorListener: () -> Unit) {
    checkConstraintResult(this.isNotEmpty()) { errorListener() }
}
package com.form.raika.constraints

import com.form.raika.checkConstraintResult

fun <T> Collection<T>.isRequire (errorListener: () -> Unit) {
    checkConstraintResult(this.isNotEmpty()) { errorListener() }
}
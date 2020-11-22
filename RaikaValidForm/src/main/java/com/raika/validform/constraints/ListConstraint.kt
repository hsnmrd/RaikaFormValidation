package com.raika.validform.constraints

import com.raika.validform.checkConstraintResult

fun <T> Collection<T>.isRequire (errorListener: () -> Unit) {
    checkConstraintResult(this.isNotEmpty()) { errorListener() }
}
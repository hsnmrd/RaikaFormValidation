package com.raika.validform

fun checkConstraintResult(value: Boolean, errorListener: () -> Unit) {
    FormValidation.validationList.lastOrNull()?.type?.add(FormValidation.WithConstraint(value) {
        errorListener()
    })
}
package com.form.raika

fun checkConstraintResult(value: Boolean, errorListener: () -> Unit) {
    FormValidation.validationList.lastOrNull()?.type?.add(FormValidation.WithConstraint(value) {
        errorListener()
    })
}
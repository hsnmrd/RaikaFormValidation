package com.raika.validform

fun checkConstraintResult(value: Boolean, errorListener: () -> Unit) {
    RaikaFormValidation.validationList.lastOrNull()?.type?.add(RaikaFormValidation.WithConstraint(value) {
        errorListener?.invoke()
    })
}
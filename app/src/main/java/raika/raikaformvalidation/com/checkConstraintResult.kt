package raika.raikaformvalidation.com

fun checkConstraintResult(value: Boolean, errorListener: () -> Unit) {
    FormValidation.validationList.lastOrNull()?.type?.add(WithConstraint(value) {
        errorListener()
    })
}
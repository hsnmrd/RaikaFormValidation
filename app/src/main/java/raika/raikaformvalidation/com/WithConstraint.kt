package raika.raikaformvalidation.com

data class WithConstraint(
    var constraint:  Boolean,
    var notValidListener: (() -> Unit)? = null,
)
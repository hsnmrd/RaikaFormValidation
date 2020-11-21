package raika.raikaformvalidation.com

class FormValidation {

    companion object {
        var validationList = emptyList<FormValidationModel<*>>().toMutableList()
    }

    fun <T> addConstraint(
        target: T,
        type: T.() -> Unit,
    ): FormValidation {
        validationList.add(FormValidationModel(target))
        type(target)
        return this
    }

    fun isValidate(listener: () -> Unit) {
        var isValid = true
        validationList.forEach loop@{ validationItem ->
            validationItem.type.forEach {
                isValid = (it.constraint.also { result ->
                        if (!result) {
                            it.notValidListener?.invoke()
                        }
                    })
                if (!isValid) {
                    isValid = false
                    return@loop
                }
            }
        }

        validationList.clear()
        if (isValid) {
            listener()
        }
    }

    data class FormValidationModel<T>(
        var target: T,
        var type: MutableList<WithConstraint> = emptyList<WithConstraint>().toMutableList()
    )


}
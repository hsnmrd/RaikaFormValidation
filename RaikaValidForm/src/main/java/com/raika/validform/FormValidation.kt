package com.raika.validform

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
        validationList.forEach { validationItem ->
            run process@{
                validationItem.type.forEach {
                    it.constraint.also { result ->
                        isValid = result.and(isValid)
                        if (!result) {
                            it.notValidListener?.invoke()
                            isValid = false
                            return@process
                        }
                    }

                }
            }
        }
        validationList.clear()
        if (isValid) listener()
    }

    data class FormValidationModel<T>(
        var target: T,
        var type: MutableList<WithConstraint> = emptyList<WithConstraint>().toMutableList(),
    )

    data class WithConstraint(
        var constraint: Boolean,
        var notValidListener: (() -> Unit)? = null,
    )

}
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
        validationList.forEach { validationItem ->
            validationItem.type.forEach {
                it.constraint.also { result ->
                    if (!result) {
                        it.notValidListener?.invoke()
                        return
                    }
                }
            }
        }

        validationList.clear()
        listener()
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
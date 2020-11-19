package raika.raikaformvalidation.com

sealed class FormValidationType {
    
    abstract val order: Int
    abstract var notValidListener: ((FormValidationListener) -> Unit)?
    
    class WithNotNullLimit(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 0
        override var notValidListener = notValid
    }
    
    class WithRequiredLimit(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 1
        override var notValidListener = notValid
    }
    
    class WithCheckedLimit(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 2
        override var notValidListener = notValid
    }
    
    class WithMinLengthLimit(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 3
        override var notValidListener = notValid
    }
    
    class WithMaxLengthLimit(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 4
        override var notValidListener = notValid
    }
    
    class WithEqualLengthLimit(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 5
        override var notValidListener = notValid
    }
    
    class WithCustomLimit(
        var limit: () -> Boolean,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 6
        override var notValidListener = notValid
    }
    
    class WithValidEmailLimit(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 7
        override var notValidListener = notValid
    }
    
    class WithConfirmLimit(
        var password: String,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 8
        override var notValidListener = notValid
    }
}
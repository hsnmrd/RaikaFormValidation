package raika.raikaformvalidation.com

sealed class FormValidationType {
    
    abstract val order: Int
    abstract var notValidListener: ((FormValidationListener) -> Unit)?
    
    class WithNotNullFilter(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 0
        override var notValidListener = notValid
    }
    
    class WithRequiredFilter(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 1
        override var notValidListener = notValid
    }
    
    class WithCheckedFilter(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 2
        override var notValidListener = notValid
    }
    
    class WithMinLengthFilter(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 3
        override var notValidListener = notValid
    }
    
    class WithMaxLengthFilter(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 4
        override var notValidListener = notValid
    }
    
    class WithEqualLengthFilter(
        var length: Int,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 5
        override var notValidListener = notValid
    }
    
    class WithCustomFilter(
        var filter: () -> Boolean,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 6
        override var notValidListener = notValid
    }
    
    class WithValidEmailFilter(
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 7
        override var notValidListener = notValid
    }
    
    class WithConfirmFilter(
        var password: String,
        notValid: ((FormValidationListener) -> Unit)? = null,
    ) : FormValidationType() {
        override val order = 8
        override var notValidListener = notValid
    }
}
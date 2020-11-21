package raika.raikaformvalidation.com

sealed class FormValidationViews {
    
    class ConfirmPassword(
        var password: String
    ) : FormValidationViews()
    
    class MinCharacter(
        var length: Int
    ) : FormValidationViews()
    
    class MaxCharacter(
        var length: Int
    ) : FormValidationViews()
    
    class Custom(
        var filter: () -> Boolean
    ) : FormValidationViews()
    
    object NotEmpty : FormValidationViews()
    
}
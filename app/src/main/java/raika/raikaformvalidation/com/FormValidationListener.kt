package raika.raikaformvalidation.com

import android.view.View

data class FormValidationListener (
    var targetView: View?, var message: String, var type: FormValidationType
)
package raika.formvalidation.com.constraints

import android.widget.CheckBox
import raika.formvalidation.com.checkConstraintResult

fun CheckBox.isChecked(errorListener: () -> Unit) {
    checkConstraintResult(this.isChecked) { errorListener() }
}
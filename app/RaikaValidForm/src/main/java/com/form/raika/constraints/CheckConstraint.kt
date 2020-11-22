package com.form.raika.constraints

import android.widget.CheckBox
import com.form.raika.checkConstraintResult

fun CheckBox.isChecked(errorListener: () -> Unit) {
    checkConstraintResult(this.isChecked) { errorListener() }
}
package com.raika.validform.constraints

import android.widget.CheckBox
import com.raika.validform.checkConstraintResult

fun CheckBox.isChecked(errorListener: (() -> Unit)? = null) {
    checkConstraintResult(this.isChecked) { errorListener?.invoke() }
}
package raika.raikaformvalidation.com

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import java.util.regex.Matcher
import java.util.regex.Pattern

object FormValidation {
    
    private var validationList = emptyList<FormValidationModel<*>>().toMutableList()
    private var customUI: ((FormValidationListener) -> Unit)? = null
    
    fun onValidateFailed(customUI: ((FormValidationListener) -> Unit)? = null): FormValidation {
        this.customUI = customUI
        return this
    }
    
    fun <T> addLimit(
        type: FormValidationType,
        target: T,
        targetError: T? = null,
        message: String,
    ): FormValidation {
        if (targetError == null) {
            validationList.add(FormValidationModel(type, target, target, message))
        } else {
            validationList.add(FormValidationModel(type, target, targetError, message))
        }
        return this
    }
    
    fun isValidate(listener: () -> Unit) {
        var isValid = true
        validationList.sortBy { it.type.order }
        validationList.forEach { validationItem ->
            if (!isValid) {
                return@forEach
            }
            isValid = checkType(
                target = validationItem.target,
                isStringType = {
                    handleStringType(validationItem, it)
                },
                isCheckBoxType = {
                    handleCheckBox(validationItem, it)
                },
                isEditTextType = {
                    handleEditText(validationItem, it)
                },
                isListType = {
                    handleList(validationItem, it)
                }
            )
        }
        
        if (isValid) {
            listener()
        }
    }
    
    private fun handleList(validationItem: FormValidationModel<*>, it: Collection<*>): Boolean {
        when (validationItem.type) {
            is FormValidationType.WithValidEmailLimit,
            is FormValidationType.WithMinLengthLimit,
            is FormValidationType.WithMaxLengthLimit,
            is FormValidationType.WithEqualLengthLimit,
            is FormValidationType.WithCheckedLimit,
            is FormValidationType.WithNotNullLimit,
            is FormValidationType.WithConfirmLimit -> {
                throw IllegalArgumentException("FormValidation: Lists Just Supports [FormValidationType.Custom] and FormValidationType.NotEmpty Types")
            }
            is FormValidationType.WithCustomLimit -> {
                return (validationItem.type as FormValidationType.WithCustomLimit).limit.invoke()
                    .also { result ->
                        handleFilter(validationItem, result)
                    }
            }
            is FormValidationType.WithRequiredLimit -> {
                return it.isNotEmpty().also { result ->
                    handleFilter(validationItem, result)
                }
            }
        }
    }
    
    private fun handleEditText(validationItem: FormValidationModel<*>, it: TextView): Boolean {
        when (validationItem.type) {
    
            is FormValidationType.WithValidEmailLimit -> {
                val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern: Pattern =
                    Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher: Matcher = pattern.matcher(it.text.trim())
                return matcher.matches().also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithConfirmLimit -> {
                return (it.text.toString() == (validationItem.type as FormValidationType.WithConfirmLimit).password).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithMinLengthLimit -> {
                return (it.text.trim().length >= (validationItem.type as FormValidationType.WithMinLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithMaxLengthLimit -> {
                return (it.text.trim().length <= (validationItem.type as FormValidationType.WithMaxLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithEqualLengthLimit -> {
                return (it.text.trim().length == (validationItem.type as FormValidationType.WithEqualLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithCustomLimit -> {
                return (validationItem.type as FormValidationType.WithCustomLimit).limit.invoke()
                    .also { result ->
                        handleFilter(validationItem, result)
                    }
            }
    
            is FormValidationType.WithRequiredLimit -> {
                return (it.text.trim().isNotEmpty()).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithCheckedLimit,
            is FormValidationType.WithNotNullLimit -> throw IllegalArgumentException("FormValidation: EditText View Not Supports FormValidationType.Checked Type")
        }
    }
    
    
    private fun handleCheckBox(validationItem: FormValidationModel<*>, it: CheckBox): Boolean {
        when (validationItem.type) {
    
            is FormValidationType.WithCheckedLimit -> {
                return it.isChecked.also { result ->
                    handleFilter(validationItem, result)
                }
            }
            
            else -> throw IllegalArgumentException("FormValidation: CheckBox View Just Supports FormValidationType.Checked Type")
            
        }
    }
    
    private fun handleStringType(validationItem: FormValidationModel<*>, it: String?): Boolean {
        when (validationItem.type) {
    
            is FormValidationType.WithNotNullLimit -> {
                return (!it.isNullOrEmpty() && it != "null").also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithValidEmailLimit -> {
                val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern: Pattern =
                    Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher: Matcher = pattern.matcher(it.toString())
                return matcher.matches().also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithConfirmLimit -> {
                return (it == (validationItem.type as FormValidationType.WithConfirmLimit).password).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithMinLengthLimit -> {
                return (it.toString().length >= (validationItem.type as FormValidationType.WithMinLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithMaxLengthLimit -> {
                return (it.toString().length <= (validationItem.type as FormValidationType.WithMaxLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithEqualLengthLimit -> {
                return (it.toString().length == (validationItem.type as FormValidationType.WithEqualLengthLimit).length).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithCustomLimit -> {
                return (validationItem.type as FormValidationType.WithCustomLimit).limit.invoke()
                    .also { result ->
                        handleFilter(validationItem, result)
                    }
            }
    
            is FormValidationType.WithRequiredLimit -> {
                return (it.toString().isNotEmpty()).also { result ->
                    handleFilter(validationItem, result)
                }
            }
    
            is FormValidationType.WithCheckedLimit -> throw IllegalArgumentException("FormValidation: EditText View Not Supports FormValidationType.Checked Type")
        }
    }
    
    private fun handleFilter(validationItem: FormValidationModel<*>, result: Boolean) {
        val targetView = if (validationItem.targetError is View) validationItem.targetError as View else null
        if (!result) {
            if (validationItem.type.notValidListener == null) {
                showError(validationItem)
            } else {
                validationItem.type.notValidListener?.invoke(
                    FormValidationListener(
                        targetView,
                        validationItem.message,
                        validationItem.type
                    )
                )
            }
        }
    }
    
    private fun showError(validationItem: FormValidationModel<*>) {
        val targetView = if (validationItem.targetError is View) validationItem.targetError as View else null
        if (customUI == null) {
            Log.w("Raika Form Validation","customErrorUI function didn't called")
        } else {
            customUI?.invoke(
                FormValidationListener(
                    targetView,
                    validationItem.message,
                    validationItem.type
                )
            )
        }
    }
    
    private fun <T> checkType(
        target: T,
        isEditTextType: (editText: TextView) -> Boolean,
        isCheckBoxType: (checkBox: CheckBox) -> Boolean,
        isListType: (list: Collection<*>) -> Boolean,
        isStringType: (value: String?) -> Boolean,
    ): Boolean {
        return when (target) {
    
            is EditText,
            is AppCompatEditText,
            is TextInputEditText,
            is TextView,
            is AppCompatTextView,
            is MultiAutoCompleteTextView,
            is MaterialTextView -> {
                isEditTextType(target as TextView)
            }
    
            is CheckBox,
            is AppCompatCheckBox,
            is MaterialCheckBox -> {
                isCheckBoxType(target as CheckBox)
            }
    
            is MutableList<*>,
            is List<*>,
            is ArrayList<*> -> {
                isListType(target as Collection<*>)
            }
    
            is String? -> {
                isStringType(target)
            }
            
            else -> throw IllegalArgumentException("FormValidation: Target Type Not Supports")
        }
    }
    
    
    private data class FormValidationModel<T>(
        var type: FormValidationType,
        var target: T,
        var targetError: T?,
        var message: String = "",
    )
    
    
}
# FormValidation


[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
![Language](https://img.shields.io/badge/language-Kotlin-red.svg)
[![](https://jitpack.io/v/hsnmrd/RaikaFormValidation.svg)](https://jitpack.io/#hsnmrd/RaikaFormValidation)

there are lots of boring ways to check form validation!  
**this library** offers an easy form validation for android apps.  
the library will work with  
- **TextViews**: TextView, AppCompatTextView, MultiAutoCompleteTextView, MaterialTextView
- **EditTexts**: EditText, AppCompatEditText, TextInputEditText
- **CheckBoxs**: CheckBox, AppCompatCheckBox, MaterialCheckBox
- **Lists**: MutableList, List, ArrayList
- **string**  

# Contents
- [how to use](https://github.com/hsnmrd/RaikaFormValidation#usage)  
- [functions](https://github.com/hsnmrd/RaikaFormValidation#functions)  
  [error for each limit](https://github.com/hsnmrd/RaikaFormValidation#error-for-each-limit)  


# Usage  
- first add dependency to your build.gradle file  
	```groovy
	implementation '-'
	```
  
- then use ```FormValidation``` **class** with ```addLimit``` & ```isValidate``` & ```onValidateFailed``` **functions**.   
  here is an example:
  ```kotlin
  FormValidation()
	.addLimit(
	    type = FormValidationType.WithRequiredLimit(),
	    target = this.iet_activity_root_first_name,
	    message = "first name is required"
	)
	.onValidateFailed {
	    Log.e("error", "${it.message} with type: ${it.type}")
	    // todo : show some error to user
	}
	.isValidate {
	    // todo : form is valid
	}
  ```
  
  
# Functions  
#### 1. addLimit
```kotlin
fun <T> addLimit(
        type: FormValidationType,
        target: T,
        targetError: T? = null,
        message: String,
    )
```
    

### Params  
#### 1-1. ```type```: there are some const filter types  
- ```WithNotNullLimit```: (for ```String```) check if String is null
- ```WithCheckedLimit```: (for ```checkbox```) check if checkbox is checked
- ```WithRequiredLimit```: check if field has value
- ```WithMinLengthLimit```: check if field has minimum limit character
- ```WithMaxLengthLimit```: check if field has maximum limit character
- ```WithEqualLengthLimit```: check if field has spesefic length
- ```WithValidEmailLimit```: check if email is valid 
- ```WithConfirmLimit```: (for ```confirm password``` or ```confirm email```) check if field value is same as passed data 
- ```WithCustomLimit```: make your own filter


#### error for each limit  
all these **types** have a ```lambda```, which is ```nullable``` and it helps to show **customize error** for each limit.  
by passing ```lambda```, ```onValidateFailed``` function won't call for that specific **limit**.  
here is an example:
```kotlin
.addLimit(
    type = FormValidationType.WithRequiredLimit {
    	// here show specific error for this filter - has access to [it.type] and [it.message] and [it.targetView] 
	this.til_activity_root_first_name.isErrorEnabled = true
	this.til_activity_root_first_name.error = it.message
    },
    target = this.iet_activity_root_first_name,
    message = "first name is required"
)
```  

#### 1-2. ```target```: a field that needs to have a constraint


#### 1-3. ```targetError```: if an error is going to be shown on a different target view, then ```targetError``` will be useful.  
it's a nullable argument, so if ```target``` is equal to ```targetError```, then there is no need to pass ```targetError```. 
by passing ```target``` without ```targetError```, ```targetError``` will get ```target``` value. 
here is an example, with different ```target``` and ```targetError```
```kotlin
.addLimit(
    type = FormValidationType.WithRequiredLimit(),
    target = this.iet_activity_root_first_name, // passed TextInputEditText
    targetError = this.til_activity_root_first_name, // passed TextInputLayout
    message = "first name is required"
)
```  

#### 1-4. ```message```: pass your error message 








----------  
  
  
#### 2. onValidateFailed  
```kotlin
fun onValidateFailed(customUI: ((FormValidationListener) -> Unit)? = null): FormValidation {
	this.customUI = customUI
	return this
}
```
handle errors with **onValidateFailed** function  
as mentioned in [1-1](https://github.com/hsnmrd/RaikaFormValidation#1-1-type-there-are-some-const-filter-types) this function will call **if** the lambda of ```type``` argument didn't passed.  
```type```, ```targetview```, ```message``` is accessable in this function.  
  ```kotlin
  FormValidation()
	.addLimit(
	    type = FormValidationType.WithRequiredLimit(),
	    target = this.iet_activity_root_first_name,
	    message = "first name is required"
	)
	.onValidateFailed {
	    Log.e("error", "${it.message} with type: ${it.type}")
	    // todo : show some error to user
	}
  ```  








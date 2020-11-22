# FormValidation


[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
![Language](https://img.shields.io/badge/language-Kotlin-red.svg)
[![](https://jitpack.io/v/hsnmrd/RaikaFormValidation.svg)](https://jitpack.io/#hsnmrd/RaikaFormValidation)

there are lots of boring ways to check form validation!  
**this library** offers an easy validation for android apps.  
the library will work with  
- **```TextViews```** TextView, AppCompatTextView, MultiAutoCompleteTextView, MaterialTextView
- **```EditTexts```** EditText, AppCompatEditText, TextInputEditText
- **```CheckBoxs```** CheckBox, AppCompatCheckBox, MaterialCheckBox
- **```Collection```** MutableList, List, ArrayList
- **```String```**  
- **```Int```**  
- **```Float```**  
- **```Double```**  
- **```Date```**  

# Contents
- [how to use](https://github.com/hsnmrd/RaikaFormValidation#usage)  
- [functions](https://github.com/hsnmrd/RaikaFormValidation#functions)  
  [error for each limit](https://github.com/hsnmrd/RaikaFormValidation#error-for-each-limit)  


# Usage  

- Step 1. Add the JitPack repository to your build file.  
Add it in your root build.gradle at the end of repositories.  
```groovy
allprojects {
	repositories {
		..
		maven { url 'https://jitpack.io' }
	}
}
```
- Step 2. Add the dependency
```groovy
dependencies {
	implementation 'com.github.hsnmrd:RaikaFormValidation:0.0.1'
}
```  
  
- Step 3. use ```FormValidation``` **class** and ```addConstraint```, ```isValidate``` **functions**.   
```kotlin
FormValidation()
	.addConstraint(etFirstName) {
	    isRequire {
		// todo : control error
	    }
	}
	.addConstraint(etEmail) {
	    isEmail {
		// todo : control error
	    }
	}
	.isValidate {

	}
```
  
  
# Functions  
#### 1. addConstraint
```kotlin
fun <T> addConstraint(
	target: T,
	type: T.() -> Unit,
): FormValidation {}
```
    
### Params  
- ```target``` pass the **target** you want to **limit**.
- ```type``` some **restrictions** are available due to the target passed.  
 
 
### Restrictions  
- ```EditText```, ```TextView```    
	**```isRequire {}```**  **```isEmail {}```**  **```isLengthAtMost {}```**  **```isLengthLessThan {}```**  **```isLengthGreaterThan {}```**  **```isLengthIn {}```**  **```isLengthEqual {}```**  **```isContaining {}```**  **```isConfirm {}```**  **```isContaining {}```**  

- ```String```    
	**```isNotNull {}```**  **```isRequire {}```**  **```isEmail {}```**  **```isLengthAtMost {}```**  **```isLengthLessThan {}```**  **```isLengthGreaterThan {}```**  **```isLengthIn {}```**  **```isLengthEqual {}```**  **```isContaining {}```**  **```isConfirm {}```**  

- ```Collection```    
	**```isRequire {}```**  
	
- ```CheckBox```    
	**```isChecked {}```**  
	
- ```Int``` ```Float``` ```Double``` ```Date```
	**```isNotNull {}```**  **```isAtMost {}```**  **```isLessThan {}```**  **```isAtLeast {}```**  **```isGreaterThan {}```**  **```isIn {}```**  **```isEqual {}```**  
	
	

#### 2. isValidate  
```kotlin
fun isValidate(listener: () -> Unit) {}
```
	
	
	
	
	
	
	
	

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








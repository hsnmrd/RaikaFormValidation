# FormValidation


[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
![Language](https://img.shields.io/badge/language-Kotlin-red.svg)
[![](https://jitpack.io/v/hsnmrd/RaikaFormValidation.svg)](https://jitpack.io/#hsnmrd/RaikaFormValidation)

there are lots of boring ways to check form validation!  
**this library** offers an easy way to validate form for android apps.  
the library will work with  
- **```TextViews```**  
- **```EditTexts```**  
- **```CheckBoxs```**  
- **```Collection```**  
- **```String```**  
- **```Int```**  
- **```Float```**  
- **```Double```**  
- **```Date```**  
- **```Additional Target```** [```check```](https://github.com/hsnmrd/RaikaFormValidation#supporting-additional-target) 

# Contents
- [How To Use](https://github.com/hsnmrd/RaikaFormValidation#usage)  
- [Functions](https://github.com/hsnmrd/RaikaFormValidation#functions)  
- [Restrictions](https://github.com/hsnmrd/RaikaFormValidation#restrictions) 
- [Supporting Additional Target](https://github.com/hsnmrd/RaikaFormValidation#supporting-additional-target) 


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
	implementation 'com.github.hsnmrd:RaikaFormValidation:$version'
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
	    isRequire {
		// todo : control error
	    }
	}
	.isValidate {
		// form is valid
	}
```
  
  
# Functions  
#### 1. ```addConstraint```: add the Restriction to specific target  
```kotlin
fun <T> addConstraint(
	target: T,
	type: T.() -> Unit,
): FormValidation {}
```
    
### Params  
- ```target``` pass the **target** which is going to have a **limit**.
- ```type``` some **restrictions** are available due to the target passed.  
 
 
### Restrictions
- ```EditText```, ```TextView```   

	**```isRequire {}```**  **```isEmail {}```**  **```isLengthAtMost {}```**  **```isLengthLessThan {}```**  **```isLengthGreaterThan {}```**  **```isLengthIn {}```**  **```isLengthEqual {}```**  **```isContaining {}```**  **```isEqual {}```**  **```isContaining {}```**  **```isContainingNumber {}```**  **```isContainingUpperCase {}```**  

- ```String```    

	**```isNotNull {}```**  **```isRequire {}```**  **```isEmail {}```**  **```isLengthAtMost {}```**  **```isLengthLessThan {}```**  **```isLengthGreaterThan {}```**  **```isLengthIn {}```**  **```isLengthEqual {}```**  **```isContaining {}```**  **```isEqual {}```** **```isContainingNumber {}```**  **```isContainingUpperCase {}```**  


- ```Collection```    

	**```isRequire {}```**  **```isSizeAtMost {}```**  **```isSizeLessThan {}```**  **```isSizeAtLeast {}```**  **```isSizeGreaterThan {}```**  **```isSizeIn {}```**  **```isSizeEqual {}```**  **```isNotNull {}```**  
	
- ```CheckBox```    

	**```isChecked {}```**  
	
- ```Int``` ```Float``` ```Double``` ```Date```

	**```isNotNull {}```**  **```isAtMost {}```**  **```isLessThan {}```**  **```isAtLeast {}```**  **```isGreaterThan {}```**  **```isIn {}```**  **```isEqual {}```**  
	
	

#### 2. ```isValidate```: check if the form is valid	 
```kotlin
fun isValidate(listener: () -> Unit) {}
```  

---------- 
	
	
# Supporting Additional Target  
if there is a **type** which is *not supported yet*, here is a way to implement your custom **restriction**.  
- make your **custom restriction** by using **```checkConstraintResult ()```** function and pass a **```condition```** as an argument.  
	**```checkConstraintResult```'s** callback will call if the passed argument (```condition```) is **false**.  
	so **improve** your restriction by making a **lambda** which is called **```errorListener```** as shown below and call it when the condition result is **false**.  
```kotlin
fun Type.yourRestrictionName(errorListener: () -> Unit) {
    checkConstraintResult(condition) { errorListener() }
}  
```  

for more explanation check one of written restriction.  
``` kotlin
fun TextView.isRequire(errorListener: () -> Unit) {
    checkConstraintResult(this.text.toString().trim().isNotEmpty()) { errorListener() }
}
``` 
**```Type```** : ```TextView```  
**```yourRestrictionName```** : ```isRequire```  
**```condition```** : ```this.text.toString().trim().isNotEmpty()```  
	
	
	
	

package raika.raikaformvalidation.com

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private class MainActivity : AppCompatActivity(R.layout.activity_main) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        this.btn_activity_confirm_password.setOnClickListener {
            
            this.til_activity_root_first_name.isErrorEnabled = false
            this.til_activity_root_last_name.isErrorEnabled = false
            this.til_activity_root_email.isErrorEnabled = false
            this.til_activity_root_password.isErrorEnabled = false
            this.til_activity_root_confirm_password.isErrorEnabled = false
            
            FormValidation
                    .addLimit(
                            type = FormValidationType.WithRequiredLimit {
                                this.til_activity_root_first_name.isErrorEnabled = true
                                this.til_activity_root_first_name.error = it.message
                            },
                            target = this.iet_activity_root_first_name,
                            message = "first name is required"
                    )
                    .addLimit(
                            type = FormValidationType.WithRequiredLimit {
                                this.til_activity_root_last_name.isErrorEnabled = true
                                this.til_activity_root_last_name.error = it.message
                            },
                            target = this.iet_activity_root_last_name,
                            message = "last name is required"
                    )
                    .addLimit(
                            type = FormValidationType.WithValidEmailLimit {
                                this.til_activity_root_email.isErrorEnabled = true
                                this.til_activity_root_email.error = it.message
                            },
                            target = this.iet_activity_root_email,
                            message = "email is not valid"
                    )
                    .addLimit(
                            type = FormValidationType.WithRequiredLimit {
                                this.til_activity_root_password.isErrorEnabled = true
                                this.til_activity_root_password.error = it.message
                            },
                            target = this.iet_activity_root_password,
                            message = "password is required"
                    )
                    .addLimit(
                            type = FormValidationType.WithConfirmLimit(this.iet_activity_root_password.text.toString()) {
                                this.til_activity_root_confirm_password.isErrorEnabled = true
                                this.til_activity_root_confirm_password.error = it.message
                            },
                            target = this.iet_activity_root_confirm_password,
                            message = "password is not same"
                    )
                    .addLimit(
                            type = FormValidationType.WithCustomLimit(
                                    limit = {
                                        this.iet_activity_root_first_name.text.toString() != "name"
                                    },
                                    notValid = {
                                        this.til_activity_root_first_name.isErrorEnabled = true
                                        this.til_activity_root_first_name.error = it.message
                                    }
                            ),
                            target = this.iet_activity_root_first_name,
                            message = "\"name\" can not be your first name"
                    )
                    .onValidateFailed {
                        Log.e("error", "${it.message} with type: ${it.type}")
                        // todo : show some error to user
                    }
                    .isValidate {
                        // todo : form is valid
                    }
        }
        
    }
    
}

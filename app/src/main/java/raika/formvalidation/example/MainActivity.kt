package raika.formvalidation.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.raika.validform.FormValidation
import com.raika.validform.constraints.isEmail
import com.raika.validform.constraints.isRequire
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etFirstName = this.iet_activity_root_first_name
        val etEmail = this.iet_activity_root_email

        this.btn_activity_confirm_password.setOnClickListener {
            FormValidation()
                .addConstraint(etFirstName) {
                    isRequire {
                        Log.e("FormValidation", "etFirstName-isRequire")
                        // todo : control error
                    }
                }
                .addConstraint(etEmail) {
                    isRequire {
                        Log.e("FormValidation", "etEmail-isRequire")
                        // todo : control error
                    }
                    isEmail {
                        Log.e("FormValidation", "etEmail-isEmail")
                        // todo : control error
                    }
                }
                .isValidate {
                    Log.e("FormValidation", "is_validate")
                }
        }

    }

}

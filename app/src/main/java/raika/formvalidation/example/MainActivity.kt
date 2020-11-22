package raika.formvalidation.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.form.raika.constraints.isEmail
import com.form.raika.constraints.isRequire
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        this.btn_activity_confirm_password.setOnClickListener {
            com.form.raika.FormValidation()
                .addConstraint(this.iet_activity_root_first_name) {
                    isRequire {
                        // todo : control error
                    }
                }
                .addConstraint(this.iet_activity_root_email) {
                    isEmail {
                        // todo : control error
                    }
                }
                .isValidate {

                }
        }

    }

}

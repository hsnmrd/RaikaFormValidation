package raika.formvalidation.com

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.raika.validform.FormValidation
import com.raika.validform.constraints.*
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
                        // todo : control error
                    }
                    isContainingNumber { Log.e("MainActivity", "isContainingNumber-false") }
                    isContainingUpperCase { Log.e("MainActivity", "isContainingUpperCase-false") }
                }
                .addConstraint(etEmail) {
                    isRequire {
                        // todo : control error
                    }
                    isEmail {
                        // todo : control error
                    }
                }
                .isValidate {

                }
        }

    }

}

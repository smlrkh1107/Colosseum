package kun.hee.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kun.hee.colosseum.utils.ServerUtil
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_sign_up.emailEdt as emailEdt1

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        isDuplicateIdBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequesEmailDuplCheck(mContext, inputEmail, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("이메일 중복 응답", json.toString())
                    val code = json.getInt("code")

                    if (code == 200) {
                        runOnUiThread {
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.azul))
                            emailCheckResultTxt.setText(R.string.id_check_success_message)
                        }

                    }
                    else {
                        runOnUiThread { // ui변경하는건 이거안에다가 해
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.grapefruit))
                            emailCheckResultTxt.setText(R.string.id_check_fail_message)
                        }
                    }

                }
            })
        }

    }

    override fun setValues() {

    }


}

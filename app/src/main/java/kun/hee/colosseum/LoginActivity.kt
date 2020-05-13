package kun.hee.colosseum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kun.hee.colosseum.utils.ContextUtil
import kun.hee.colosseum.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            ContextUtil.setAutoLogin(mContext, isChecked)
        }




        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext, email, pw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
//                    Log.d("로그인 응답", json.toString())
                    val code = json.getInt("code")

                    if(code == 200){
                        val data = json.getJSONObject("data")
                        val token = data.getString("token")

                        ContextUtil.setUserToken(mContext, token)

                        runOnUiThread {
                            Toast.makeText(mContext, resources.getString(R.string.login_success_message), Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })

        }

    }

    override fun setValues() {
        autoLoginCheckBox.isChecked = ContextUtil.isAutoLogin(mContext) // 이걸 적어줘야 자동로그인 체크여부 저장.(앱껏다켜도)

    }


}

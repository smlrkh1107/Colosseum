package kun.hee.colosseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kun.hee.colosseum.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

     }

    override fun setValues() {
        Handler().postDelayed({
//            1. 저장된 토큰이 있나?  => 로그인 성공했었는지.
//            2. 자동 로그인을 쓰고 싶어하는지?
//              => 둘다 만족이 되어야 메인 액티비티
//              => 하나라도 틀리면 로그인 화면으로.

            if (ContextUtil.getUserToken(mContext) != "" && ContextUtil.isAutoLogin(mContext)) {
                Toast.makeText(mContext, "메인화면으로 이동해야 함", Toast.LENGTH_SHORT).show()
            }
            else {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()
            }


        }, 200)
    }


}

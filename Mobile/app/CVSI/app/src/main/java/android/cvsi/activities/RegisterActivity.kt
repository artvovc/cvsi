package android.cvsi.activities

import android.content.Intent
import android.cvsi.R
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*

class RegisterActivity:BaseActivityNoToolbar(){
    override var lay: Int = R.layout.activity_signup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn_signup.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        link_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}

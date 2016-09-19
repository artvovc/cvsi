package android.cvsi.activities

import android.content.Intent
import android.os.Bundle
import android.cvsi.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivityNoToolbar() {
    override var lay: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        link_signup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}


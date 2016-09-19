package android.cvsi.activities


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivityNoToolbar: AppCompatActivity(){
    protected abstract var lay: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(lay)
    }

    protected val context: Context
        get() = this

    protected val activity: Activity
        get() = this

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}


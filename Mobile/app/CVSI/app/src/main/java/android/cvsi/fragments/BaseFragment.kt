package android.cvsi.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


abstract class BaseFragment : Fragment() {

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
    }

    protected abstract val rootLayout: Int
    protected abstract var rootView: View?

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(rootLayout, container, false)
        return rootView
    }

    override fun getContext(): Context {
        return this.context
    }

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun snack(view: View, msg: Int, runnable: Runnable) {
        this.snack(view, this.getString(msg), runnable)
    }

    protected fun snack(view: View, msg: Int) {
        this.snack(view, this.getString(msg), null as Runnable)
    }

    protected fun snack(view: View, msg: String) {
        this.snack(view, msg, null as Runnable)
    }

    protected fun snack(view: View, msg: String, runnable: Runnable?) {
        Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE).setAction("Ok") {
            runnable?.run()
        }.show()
    }

}

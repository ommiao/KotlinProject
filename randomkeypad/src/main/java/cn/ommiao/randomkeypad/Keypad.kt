package cn.ommiao.randomkeypad

import android.support.v7.app.AppCompatActivity
import cn.ommiao.randomkeypad.activity.KeypadActivity
import org.jetbrains.anko.startActivity

class Keypad {

    companion object {

        var onKeypadClickListener:OnKeypadClickListener? = null

        fun open(activity: AppCompatActivity):Unit{
            activity.startActivity<KeypadActivity>()
        }

    }

    interface OnKeypadClickListener{
        fun onCompletedClick(passwd:String)
        fun onCancelClick()
    }

}
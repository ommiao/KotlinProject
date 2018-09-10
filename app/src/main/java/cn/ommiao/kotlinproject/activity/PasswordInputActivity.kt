package cn.ommiao.kotlinproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.ommiao.kotlinproject.R
import cn.ommiao.randomkeypad.Keypad
import kotlinx.android.synthetic.main.activity_password_input.*

class PasswordInputActivity : AppCompatActivity(), Keypad.OnKeypadClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_input)
        Keypad.onKeypadClickListener = this
        btn_input_passwd.setOnClickListener{
            Keypad.open(this)
        }
    }

    override fun onCompletedClick(passwd:String) {
        tv_passwd.setText(passwd)
    }

    override fun onCancelClick() {

    }
}

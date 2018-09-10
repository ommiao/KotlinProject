package cn.ommiao.kotlinproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.ommiao.kotlinproject.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_keypad.setOnClickListener {
            startActivity<PasswordInputActivity>()
        }
    }
}

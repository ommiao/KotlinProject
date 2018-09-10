package cn.ommiao.randomkeypad.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import cn.ommiao.randomkeypad.Keypad
import cn.ommiao.randomkeypad.R
import cn.ommiao.randomkeypad.utils.SpringUtils
import cn.ommiao.randomkeypad.widget.KeypadKey
import kotlinx.android.synthetic.main.activity_keypad.*
import kotlinx.android.synthetic.main.layout_keypad_keys.*
import java.util.*

class KeypadActivity : AppCompatActivity() {

    var numberStr:Array<String> = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    var keyMap = hashMapOf<KeypadKey, String>()
    var passwd = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keypad)
        initViews()
        bindListener()
    }

    private fun initViews():Unit{
        supportActionBar?.hide()
        setTransparentStatusBar()
        iv_close.setOnClickListener {
            finish()
        }
        initKeysView()
        ll_keypad.post {
            ll_keypad.translationY = ll_keypad.measuredHeight.toFloat()
            SpringUtils.keypadUp(ll_keypad, ll_keypad.height.toFloat(), v_blank.height.toFloat())
        }
    }

    private fun initKeysView():Unit {
        clearInputs()
        getRandomNumber()
        keyMap.clear()
        keyMap.put(key_1, numberStr.get(0))
        keyMap.put(key_2, numberStr.get(1))
        keyMap.put(key_3, numberStr.get(2))
        keyMap.put(key_4, numberStr.get(3))
        keyMap.put(key_5, numberStr.get(4))
        keyMap.put(key_6, numberStr.get(5))
        keyMap.put(key_7, numberStr.get(6))
        keyMap.put(key_8, numberStr.get(7))
        keyMap.put(key_9, numberStr.get(8))
        keyMap.put(key_0, numberStr.get(9))
        key_1.setValue(keyMap.get(key_1))
        key_2.setValue(keyMap.get(key_2))
        key_3.setValue(keyMap.get(key_3))
        key_4.setValue(keyMap.get(key_4))
        key_5.setValue(keyMap.get(key_5))
        key_6.setValue(keyMap.get(key_6))
        key_7.setValue(keyMap.get(key_7))
        key_8.setValue(keyMap.get(key_8))
        key_9.setValue(keyMap.get(key_9))
        key_0.setValue(keyMap.get(key_0))
    }

    private fun setTransparentStatusBar(){
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onPause() {
        overridePendingTransition(0 ,0)
        super.onPause()
    }

    override fun onBackPressed() {
        //do nothing
    }

    private fun clearInputs():Unit{
        iv_pw0.visibility = INVISIBLE
        iv_pw1.visibility = INVISIBLE
        iv_pw2.visibility = INVISIBLE
        iv_pw3.visibility = INVISIBLE
        iv_pw4.visibility = INVISIBLE
        iv_pw5.visibility = INVISIBLE
    }

    private fun bindListener():Unit{
        for (key in keyMap.keys){
            key.setOnClickListener {
                inputNumber(keyMap.get(key))
                checkCompleted()
            }
        }
        key_del.setOnClickListener {
            deleteOneNumber()
        }
    }

    private fun inputNumber(key:String?):Unit{
        passwd += key
        when(passwd.length){
            1 -> {
                iv_pw0.visibility = VISIBLE
            }
            2 -> {
                iv_pw1.visibility = VISIBLE
            }
            3 -> {
                iv_pw2.visibility = VISIBLE
            }
            4 -> {
                iv_pw3.visibility = VISIBLE
            }
            5 -> {
                iv_pw4.visibility = VISIBLE
            }
            6 -> {
                iv_pw5.visibility = VISIBLE
            }
        }
    }

    private fun deleteOneNumber():Unit{
        val len = passwd.length
        if (len == 0){
            return
        }
        //移除最后一个字符
        passwd = passwd.removeLastOne()
        when(len - 1){
            0 -> {
                iv_pw0.visibility = INVISIBLE
            }
            1 -> {
                iv_pw1.visibility = INVISIBLE
            }
            2 -> {
                iv_pw2.visibility = INVISIBLE
            }
            3 -> {
                iv_pw3.visibility = INVISIBLE
            }
            4 -> {
                iv_pw4.visibility = INVISIBLE
            }
            5 -> {
                iv_pw5.visibility = INVISIBLE
            }
        }
    }

    private fun checkCompleted():Unit{
        if(passwd.length == 6){
            Keypad.onKeypadClickListener?.onCompletedClick(passwd)
            finish()
        }
    }

    private fun getRandomNumber():Unit{
        val n = 10;
        var i = 0
        var j = 0
        var t = 1000;
        while (t-- > 0){
            i = Random().nextInt(10001)%n
            j = Random().nextInt(10001)%n
            swap(i, j)
        }
    }

    private fun swap(i:Int, j:Int):Unit{
        val temp = numberStr.get(i)
        numberStr.set(i, numberStr.get(j))
        numberStr.set(j, temp)
    }

    private fun String.removeLastOne():String{
        var newStr = ""
        if(this.length > 0){
            newStr = this.substring(0, this.length - 1)
        }
        return newStr
    }

}

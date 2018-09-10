package cn.ommiao.randomkeypad.utils

import cn.ommiao.randomkeypad.MyApplication

class UIUtils {

    companion object {

        private val context = MyApplication.context

        fun dip2px(dip:Int):Int{
            val scale = context!!.resources!!.displayMetrics!!.density
            return (dip * scale + 0.5f).toInt()
        }

    }

}
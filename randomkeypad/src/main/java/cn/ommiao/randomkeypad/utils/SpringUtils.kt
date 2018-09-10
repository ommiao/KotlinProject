package cn.ommiao.randomkeypad.utils

import android.view.View
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem

class SpringUtils {

    companion object {

        val ANIM_UP = 1 //键盘升起
        val ANIM_DOWN = 0 //键盘降下

        val tension = 20.00 //拉力
        val friction = 5.00 //摩擦力

        val springSystem:SpringSystem = SpringSystem.create()

        fun keypadUp(v:View, heightPx:Float, blankPx:Float):Unit{
            keypadMove(v, ANIM_UP, heightPx, blankPx)
        }

        fun keypadDown(v:View, heightPx:Float, blankPx:Float):Unit{
            keypadMove(v, ANIM_DOWN, heightPx, blankPx)
        }

        fun keypadMove(v: View, type:Int, heightPx: Float, blankPx:Float):Unit{
            var from : Float
            var to : Float
            when(type){
                ANIM_UP -> {
                    from = heightPx
                    to = blankPx
                }
                ANIM_DOWN -> {
                    from = blankPx
                    to = heightPx
                }
                else -> {
                    from = 0f
                    to = 0f
                }
            }
            val spring:Spring = springSystem.createSpring()
            spring.currentValue = from.toDouble()
            spring.springConfig = SpringConfig.fromOrigamiTensionAndFriction(tension, friction)
            spring.setEndValue(to.toDouble())
            spring.addListener(object : SimpleSpringListener(){
                override fun onSpringUpdate(spring: Spring?) {
                    v.translationY = spring!!.currentValue.toFloat()
                }
            })
        }


    }



}
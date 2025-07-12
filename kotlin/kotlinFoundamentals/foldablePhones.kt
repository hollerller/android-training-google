/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

fun main() {    

}

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }
    
    fun switchOff() {
        isScreenLightOn = false
    }
    
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean):
	Phone() {
        override fun switchOn() {
            if (isFolded) {
                isScreenLightOn = false
            } else {
                isScreenLightOn = true
            }
        } 
    }
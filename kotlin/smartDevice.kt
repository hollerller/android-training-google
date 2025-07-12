/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {
    
    var deviceStatus = "online"
    	protected set
        
        open fun turnOn() {
        deviceStatus = "on"
    }
    
    open fun turnOff() {
        deviceStatus = "off"
        
    }
    
    open val deviceType = "unknown"
    
    constructor(name: String, category: String, statusCode:Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        	}
    	}

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
    
    
    }

    
    
    class SmartTvDevice(deviceName: String, deviceCategory: String):
    	SmartDevice(name = deviceName, category = deviceCategory) {
            
            override val deviceType = "Smart TV"
            
            private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
                
            private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
            
            fun increaseSpeakerVolume() {
                speakerVolume--
                println("Speaker volume decreased to $speakerVolume.")
            }
            
            fun decreaseSpeakerVolume() {
                speakerVolume++
                println("Speaker volume increased to $speakerVolume.")
            }
            
            fun nextChannel() {
                channelNumber++
                println("Channel number increased to $channelNumber.")
            }
            
            fun previousChannel() {
                channelNumber--
                println("Channel number decreased to $channelNumber.")
            }
            
            override fun turnOn() {
                super.turnOn()
                println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
            }
            
            override fun turnOff() {
                super.turnOff()
                println("$name turned off")
            }
            
            
            
        }
    
    class SmartLightDevice(deviceName: String, deviceCategory: String) :
    	SmartDevice(name = deviceName, category = deviceCategory) {
            
           	override val deviceType = "Smart Light"
            
            private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)
            
            fun increaseBrightness() {
                brightnessLevel++
                println("Brightness increased to $brightnessLevel")
            }
            
            fun decreaseBrightness() {
                brightnessLevel--
                println("Brightness decreased to $brightnessLevel")
            }
            
            override fun turnOn() {
                super.turnOn()
                brightnessLevel = 2
                println("$name turned on. The brightness level is $brightnessLevel.")
            }
            
            override fun turnOff() {
                super.turnOff()
                brightnessLevel = 0
                println("Smart Light turned off")
            }
        }
    
    


class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {
   
    var deviceTurnOnCount = 0
    	private set
    
    
    fun turnOnTv() {
        smartTvDevice.turnOn()
        deviceTurnOnCount++
    }
    
    fun turnOffTv() {
        smartTvDevice.turnOff()
        deviceTurnOnCount--
    }
    
    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }
    
    fun decreaseTvVolume() {
        smartTvDevice.decreaseSpeakerVolume()
    }  
 
    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }
    
    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }
    
    fun turnOnLight() {
        smartLightDevice.turnOn()
        deviceTurnOnCount++
    }
    
    fun turnOffLight() {
        smartLightDevice.turnOff()
        deviceTurnOnCount--
    }
    
    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }
    
    fun decreaseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }
    
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
    
    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }
    
}

class RangeRegulator(
	initialValue: Int, 
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {
    
    var fieldData = initialValue
    
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }
    
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
    
}

fun main() {
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Enterteinment")
    smartDevice.turnOn()
    
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
    smartDevice.printDeviceInfo()
    
    /*
	val smartTvDevice = SmartDevice(name = "Android TV", category = "Entertainment")
    println("Device name is ${smartTvDevice.name}")
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
    */
}
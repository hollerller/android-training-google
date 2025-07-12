/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

fun main() {
    val morningNotification = 51
    val eveningNotification = 135
    
    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}


fun printNotificationSummary(numberOfMessages: Int) {
    
    val messages = numberOfMessages
    
    if (messages  < 100) {
        println("You have $messages notifications.")
    } else {
        println("Your phone is blowing up! You have 99+ notifications.")
    }
    
}
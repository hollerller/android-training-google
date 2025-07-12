/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
       if (referrer == null) {
           println("Name: $name")
           println("Age: $age")
           println("Likes to play $hobby. Doesn't have a referrer.")
       } else {
           println("Name: $name")
           println("Age: $age")
           println("Likes to play $hobby. Has a referrer named ${referrer.name}, who likes to play ${referrer.hobby}.")
       }
    }
}
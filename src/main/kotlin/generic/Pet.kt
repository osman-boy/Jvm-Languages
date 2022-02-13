package parametrizied

interface Retailer<out T> {
    fun sell(): T

}

abstract class Pet(var name: String)

class Cat(name: String) : Pet(name)

class Dog(name: String) : Pet(name)

class Fish(name: String) : Pet(name)

class Contest<T : Pet> {
    val scores: MutableMap<T , Int> = mutableMapOf()


    fun addScore(t: T , score: Int = 0) {
        if (score >= 9) scores[t] = score
    }

    fun getWinners(): MutableSet<T> {
        val winners = mutableSetOf<T>()
        val highScore = scores.values.maxOrNull()
        for ((t , score) in scores) {
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}


class CatRetailer : Retailer<Cat> {
    override fun sell(): Cat {
        println("Sell Cat")
        return Cat("")
    }
}
fun main() {


    val petRetailer : Retailer<Cat> =CatRetailer()


    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Finny McGraw")

    val catContest = Contest<Cat>()

    catContest.addScore(catFuzz , 50)
    catContest.addScore(catKatsu , 45)
    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}")

    val petContest = Contest<Pet>()
    petContest.addScore(catFuzz , 50)
    petContest.addScore(fishFinny , 56)
    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}")


}
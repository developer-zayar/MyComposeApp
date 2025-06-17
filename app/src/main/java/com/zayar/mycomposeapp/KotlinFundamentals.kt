package com.zayar.mycomposeapp

fun main() {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)
    println(question1.toString())
    println(Quiz.progressText)
    Quiz.printProgressBar()

    // Practice: Classes and Collections
    val event1 =
        Event(title = "Wake up", description = "Time to get up", dayPart = DayPart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", dayPart = DayPart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", dayPart = DayPart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", dayPart = DayPart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", dayPart = DayPart.AFTERNOON, durationInMinutes = 10)
    val event6 =
        Event(title = "Check out latest Android Jetpack library", dayPart = DayPart.EVENING, durationInMinutes = 45)

    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)

    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} short events.")

    val groupEvents = events.groupBy { it.dayPart }
    groupEvents.forEach { (dayPart, events) ->
        println("Events in $dayPart: ${events.size} events")
    }

    println("Last event of the day: ${events.last().title}")
    println("Duration of first event of the day: ${events[0].durationOfEvent}")

    val colors = listOf("Red", "Green", "Blue")

    println(colors[2])

    println(colors.get(2))

    println(colors.contains("Geen"))

}

class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)

class TrueOrFalseQuestion(
    val questionText: String,
    val answer: Boolean,
    val difficulty: String
)

class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)

class MultipleChoiceQuestion(
    val questionText: String,
    val options: List<String>,
    val answer: String,
    val difficulty: String
)

class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

class Quiz {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}

val Quiz.StudentProgress.progressText: String
    get() = "${answered} of ${total} answered"

fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) { print("|") }
    repeat(Quiz.total - Quiz.answered) { print("-") }
    println()
    println(Quiz.progressText)
}

// Practice: Classes and Collections
enum class DayPart {
    MORNING,
    AFTERNOON,
    EVENING,
}

data class Event(
    val title: String,
    val description: String? = null,
    val dayPart: DayPart,
    val durationInMinutes: Int,
)

val Event.durationOfEvent: String
    get() = if (durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }



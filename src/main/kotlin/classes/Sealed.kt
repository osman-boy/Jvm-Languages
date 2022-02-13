package classes

/**
 * From official documentation:
 * Sealed class - это класс, который позволяет ограничить иерархию классов конкретным множеством подтипов,
 * каждый из которых может определять собственные свойства и функции.
 *
 * Проще говоря:
 * - это абстрактный класс, который содержит в себе другие классы.
 * - так же их называют улучшенной версией Enums.
 * - sealed основаны на enums, но освобождены от их минусов.
 *
 * Можно создавать столько подклассов, сколько необходимо для покрытия каждой ситуации.
 * Каждый подкласс может иметь несколько экземпляр, каждый из которых будет нести в себе свое собственное состояние.
 * Каждый подкласс Enum-ов имеет свой конструктор со своими индивидуальными свойствами.
 * Она так же не нуждается в блоке else.
 *
 * Основное отличие от [enum] в том, каждая константа существует как отдельный экземпляр класса,
 * Но а [sealed] классы могут иметь несколько экземпляров со своими значениями
 */
sealed class MessageType

class MessageSuccess(var msg: String) : MessageType()

class MessageFailure(var msg: String , var e: Exception) : MessageType()


fun main() {
    val messageSuccess = MessageSuccess("It worked!")
    val messageFailure = MessageFailure("Boj!" , Exception("Gone wrong!"))

    /**
     *  Если бы у нас был бы еще один под класс, то необходимо было бы и его указать
     *  в теле when, почти так же как и enum
     **/
    val myMessage = when (val randomMessage = arrayOf(messageFailure , messageSuccess).random()) {
        is MessageSuccess -> randomMessage.msg
        is MessageFailure -> "${randomMessage.msg} ${randomMessage.e}"

    }
    println(myMessage)
}


/**
 * В случае Enums - если мы захотим что бы определенная константа имела дополнительные параметры,
 * то придётся прописать его для всех других
 */
enum class Options(val percent: Int  /*val id: Int*/) {
    VOLUME(12) ,
    HIGHLIGHT(23) ,
    ENERGY(25)
}

/**
 * В случае Sealed - если мы захотим что бы определенная константа имела дополнительные параметры,
 * то можем это подстроить без проблем.
 */
sealed class Optional(val value: Int) {
    class VolumeOption(val percent: Int) : Optional(12)
    class HighlightOption : Optional(23)
    class EnergyOption(val id: Int) : Optional(25)
}

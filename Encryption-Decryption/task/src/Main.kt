package encryptdecrypt

import java.io.File

fun unicodeEncoder(mode: String, key: Int, data: String): String {
    var codedMessage = ""

    return when (mode) {
        "enc" -> {
            for (char in data) {
                codedMessage += ((char.code + key) % 256).toChar()
            }
            codedMessage
        }
        "dec" -> {
            for (char in data) {
                codedMessage += ((char.code - key) % 256).toChar()
            }
            codedMessage
        }
        else -> "Invalid action"
    }
}

fun shiftEncoder(mode: String, key: Int, data: String): String {
    var codedMessage = ""

    return when (mode) {
        "enc" -> {
            for (char in data) {
                codedMessage += if (char.code !in 97..122 && char.code !in 65..90) {
                    char
                } else {
                    if (char.code in 97..122) {
                        ((char.code + key - 97) % 26 + 97).toChar()
                    } else {
                        ((char.code + key - 65) % 26 + 65).toChar()
                    }
                }
            }
            codedMessage
        }
        "dec" -> {
            for (char in data) {
                if (char.code !in 97..122 && char.code !in 65..90) {
                    codedMessage += char
                } else {
                    codedMessage += if (char.code in 97..122) {
                        if (char.code - key >= 97) {
                            (char.code - key).toChar()
                        } else {
                            (122 - ((122 - char.code + key) % 26)).toChar()
                        }
                    } else {
                        if (char.code - key >= 65) {
                            (char.code - key).toChar()
                        } else {
                            (90 - ((90 - char.code + key) % 26)).toChar()
                        }
                    }
                }
            }
            codedMessage
        }
        else -> "Invalid action"
    }
}

fun missingValue(listOfIndexes: List<Int>): Boolean {
    for (index in listOfIndexes) {
        if (index != -1 && index % 2 == 1) return true
    }
    return false
}

fun main(args: Array<String>) {

    val modeIndex = args.indexOf("-mode")
    val keyIndex = args.indexOf("-key")
    val dataIndex = args.indexOf("-data")
    val inIndex = args.indexOf("-in")
    val outIndex = args.indexOf("-out")
    val algIndex = args.indexOf("-alg")

    val listOfIndexes = listOf(modeIndex, keyIndex, dataIndex, inIndex, outIndex)

    if (missingValue(listOfIndexes)) {
        println("Error: Argument doesn't have a value.")
    } else {
        val mode = if (modeIndex != -1) args[modeIndex + 1] else "enc"
        val key = if (keyIndex != -1) args[keyIndex + 1].toInt() else 0
        var data = if (dataIndex != -1) args[dataIndex + 1] else ""
        val alg = if (algIndex != -1) args[algIndex + 1] else "shift"

        val separator = File.separator
        val workingDirectory = System.getProperty ("user.dir")

        if (inIndex != -1 && dataIndex == -1) {
            val path = "${workingDirectory}${separator}${args[inIndex + 1]}"
            val file = File(path)
            if (!file.exists()) {
                println("Error: Input file does not exist.")
                return
            } else {
                data = file.readText()
            }
        }

        if (outIndex != -1) {
            val path = "${workingDirectory}${separator}${args[outIndex + 1]}"
            val file = File(path)
            if (alg == "unicode") {
                file.writeText(unicodeEncoder(mode, key, data))
            } else {
                file.writeText(shiftEncoder(mode, key, data))
            }
        } else {
            if (alg == "unicode") {
                print(unicodeEncoder(mode, key, data))
            } else {
                print(shiftEncoder(mode, key, data))
            }
        }
    }

}
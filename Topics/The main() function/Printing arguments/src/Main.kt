    fun main(args: Array<String>) {
        if (args.size != 3) {
            print("Invalid number of program arguments")
        } else {
            for (i in args.indices) {
                println("Argument ${i + 1}: ${args[i]}. It has ${args[i].length} characters")
            }
        }
    }
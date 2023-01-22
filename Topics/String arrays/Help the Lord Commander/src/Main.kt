fun main() {  
    val beyondTheWall = readLine()!!.split(',').map { it }.toTypedArray()
    val backFromTheWall = readLine()!!.split(',').map { it }.toTypedArray()    
    // do not touch the lines above
    // write your code here
    val sortedBeyond = beyondTheWall.sorted().toTypedArray()
    val sortedBack = backFromTheWall.sorted().toTypedArray()
    print(sortedBeyond.contentEquals(sortedBack))

}
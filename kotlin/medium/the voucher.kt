    import java.util.*
    import java.io.*
    import java.math.*

    /**
     * Auto-generated code below aims at helping you parse
     * the standard input according to the problem statement.
     **/
    data class Product(val price: Int, var picked: Int = 0) {
        fun canPick() : Boolean = picked < 3
        fun pick() : Boolean{
            if(!canPick())
                return false
            picked++
            return true
        }
        fun unpick() {
            picked = 0
        }
    }
    fun main(args : Array<String>) {
        val input = Scanner(System.`in`)
        val V = input.nextInt()
        val N = input.nextInt()
        if (input.hasNextLine()) {
            input.nextLine()
        }
        val products = mutableListOf<Product>()
        for (i in 0 until N) {
            val FNAME = input.nextLine().split(" ")
            products.add(Product(FNAME[FNAME.size - 1].toInt()))
        }
        println(findAllCombinations(products, maxSum = V))
    }

    fun findAllCombinations(products: List<Product>, totalSum: Int = 0, maxSum : Int, index : Int = 0) : Int {
        if(totalSum == maxSum) {
            return 1
        }
        if(totalSum > maxSum || index == products.size)
            return 0
        var sum = 0;
        val product = products[index]
        if(product.pick())
            sum += findAllCombinations(products, totalSum + product.price, maxSum, index)
        product.unpick()
        sum += findAllCombinations(products, totalSum, maxSum, index + 1)
        return sum
    }

    val lambda: (Long, Long) -> Long = { a, b ->
        var product: Long = a
        for (i in a + 1..b) {
            product *= i
        }
        product
    }
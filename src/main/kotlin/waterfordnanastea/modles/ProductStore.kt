package waterfordnanastea.modles

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
private var lastId = 0L

private fun getId(): Long {
    return lastId++
}

class ProductStore : ProdctstoreI {

    val products = ArrayList<Product>()

    override fun findAll(): List<Product> {
        return products
    }

    override fun findOne(id: Long): Product? {
        var foundProduct: Product? = products.find { p -> p.id == id }
        return foundProduct
    }

    override fun create(product: Product) {
        product.id = getId()
        products.add(product)
        logAll()
    }

    override fun update(product: Product) {
        var foundProduct = findOne(product.id!!)
        if (foundProduct != null) {
            foundProduct.name = product.name
            foundProduct.category = product.category
            foundProduct.number = product.number
            foundProduct.price = product.price
        }
    }

    override fun delete(product: Product) {
        products.remove(product)
    }


    internal fun logAll() {
        products.forEach { logger.info("${it}") }
    }

}

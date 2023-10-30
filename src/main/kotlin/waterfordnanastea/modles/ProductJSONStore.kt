package waterfordnanastea.modles

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import waterfordnanastea.helper.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "product.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<Product>>() {}.type
private var lastId = 0L

private fun getId(): Long {
    return lastId++
}


class ProductJSONStore : ProdctstoreI {

    var products = mutableListOf<Product>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<Product> {
        return products
    }

    override fun findOne(id: Long): Product? {
        var foundProduct: Product? = products.find { p -> p.id == id }
        return foundProduct
    }

    override fun create(product: Product) {
        product.id = getId()
        products.add(product)
        serialize()
    }

    override fun update(product: Product) {
        var foundProduct = findOne(product.id!!)
        if (foundProduct != null) {
            foundProduct.name = product.name
            foundProduct.category = product.category
            foundProduct.number = product.number
            foundProduct.price = product.price
        }
        serialize()
    }

    override fun delete(product: Product) {
        products.remove(product)
        serialize()
    }

    internal fun logAll() {
        products.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(products, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        products = Gson().fromJson(jsonString, listType)
    }
}
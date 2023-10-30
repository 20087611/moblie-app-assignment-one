package waterfordnanastea.controller

import AddProductScreen
import mu.KotlinLogging
import tornadofx.*
import waterfordnanastea.modles.Product
import waterfordnanastea.modles.ProductJSONStore
import waterfordnanastea.view.*

class ProductUIController : Controller() {
    val productStore = ProductJSONStore()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Waterford NanasTea Shop TornadoFX UI App" }
    }

    fun addProduct(name: String, category: String, number: Int, price: Int) {
        val newProduct = Product(name = name, category = category, number = number, price = price)
        productStore.create(newProduct)
        logger.info("Product Added: $newProduct")
    }

    fun updateProduct(newName: String, newCategory: String, newNumber: Int, newPrice: Int) {
        val updateProduct = Product(name = newName, category = newCategory, number = newNumber, price = newPrice)
        productStore.update(updateProduct)
            logger.info("Product Updated: $updateProduct")
    }

    fun searchProducts(searchTerm: String): List<Product> {
        return productStore.findAll().filter { product ->
            product.name.contains(searchTerm, ignoreCase = true) ||
                    product.category.contains(searchTerm, ignoreCase = true)
        }
    }

    fun deleteProduct(productId: Long) {
        val productToDelete = productStore.findOne(productId)
        if (productToDelete != null) {
            productStore.delete(productToDelete)
            logger.info("Product Deleted: $productToDelete")
        } else {
            logger.info("Product Not Found with ID: $productId")
        }
    }




    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddProductScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddProductScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListProductScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        productStore.logAll()
    }

    fun closeList() {
        runLater {
            find(ListProductScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadUpdateScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateProductScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeUpdate() {
        runLater {
            find(UpdateProductScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

   fun loadSearchScreen() {
       runLater {
           find(MenuScreen::class).replaceWith(SearchProductScreen::class, sizeToScene = true, centerOnScreen = true)
       }
    }

    fun closeSearch() {
        runLater {
            find(SearchProductScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadDeleteScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteProductScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeDelete() {
        runLater {
            find(DeleteProductScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }



}

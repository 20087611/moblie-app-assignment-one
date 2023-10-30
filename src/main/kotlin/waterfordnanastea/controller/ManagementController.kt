package waterfordnanastea.controller

import mu.KotlinLogging
import waterfordnanastea.modles.Product
import waterfordnanastea.modles.ProductJSONStore
import waterfordnanastea.view.ProductView

class ManagementController{
    // 实例化数据访问对象
    val product = ProductJSONStore()
    val logger = KotlinLogging.logger {}
    val productView = ProductView()
    //val product = ProductStore()


    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Placemark Console App" }
    }

    fun menu() :Int { return productView.menu() }
    init {
        logger.info { "Launching Waterford NanasTea Shop App" }
        println("NanasTea Kotlin App ")
    }

    // 添加新产品
    fun add(){
        var aProduct = Product()

        if (productView.addProductData(aProduct))
           product.create(aProduct)
        else
            logger.info("Product Not Added")
    }

    fun update() {
        productView.listProducts(product)

        var searchId = productView.getId()
        val aProduct = search(searchId)

        if(aProduct != null) {
            if(productView.updateProduct(aProduct)) {
                product.update(aProduct)
                productView.showProducts(aProduct)
                logger.info("Product Updated : [ $aProduct]")
            }
            else
                logger.info("Product Not Updated")
        }
        else
            println("Product Not Updated...")
    }

    fun list() {
        productView.listProducts(product)
    }

    fun search() {
        val searchId = productView.getId()
        val aProduct = search(searchId)

        if (aProduct != null) {
            productView.showProducts(aProduct)
        } else {
            println("Product with ID $searchId not found.")
        }
    }

    fun search(id: Long): Product? {
        return product.findOne(id)
    }

    fun delete() {
        productView.listProducts(product)
        var searchId = productView.getId()
        val aProduct = search(searchId)

        if(aProduct != null) {
            product.delete(aProduct)
            println( "Product Deleted")
        }
        else
            println("Product Not Deleted" )
    }

}

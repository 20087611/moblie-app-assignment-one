package waterfordnanastea.view

import waterfordnanastea.modles.Product
import tornadofx.*
import waterfordnanastea.controller.ProductUIController

class ListProductScreen : View("List Products") {

    private val productUIController: ProductUIController by inject()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        val data = productUIController.productStore.findAll().observable()
        tableview(data) {
            readonlyColumn("ID", Product::id)
            readonlyColumn("Name", Product::name)
            readonlyColumn("Category", Product::category)
            readonlyColumn("Number", Product::number)
            readonlyColumn("Price", Product::price)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    productUIController.closeList()
                }
            }
        }
    }
}

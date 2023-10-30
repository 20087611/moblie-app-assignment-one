package waterfordnanastea.view

import javafx.beans.property.SimpleLongProperty
import tornadofx.*
import waterfordnanastea.controller.ProductUIController

class DeleteProductScreen : View("Delete Product") {
    private val productUIController: ProductUIController by inject()
    private val productId = SimpleLongProperty()

    override val root = form {
        fieldset("Delete Product") {
            field("Product ID") {
                textfield(productId)
            }
            button("Delete") {
                action {
                    productUIController.deleteProduct(productId.value)
                    // 显示删除成功的消息或处理错误
                }
            }
            button("Cancel") {
                action {
                    // 关闭当前视图或返回上一个视图
                    productUIController.closeDelete()
                }
            }
        }
    }
}

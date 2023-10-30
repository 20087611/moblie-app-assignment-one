package waterfordnanastea.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import waterfordnanastea.controller.ProductUIController
import waterfordnanastea.modles.Product
import tornadofx.*

class UpdateProductScreen : View("Update Product") {
    private val model = ViewModel()
    private val _id = model.bind { SimpleStringProperty() }
    private val _name = model.bind { SimpleStringProperty() }
    private val _category = model.bind { SimpleStringProperty() }
    private val _number = model.bind { SimpleIntegerProperty() }
    private val _price = model.bind { SimpleIntegerProperty() }
    private val productUIController: ProductUIController by inject()

    override val root = form {
        fieldset("Update Product") {
            field("Product ID") {
                textfield(_id).validator {
                    if (it.isNullOrBlank()) error("ID is required") else null
                }
            }
            field("Name") {
                textfield(_name)
            }
            field("Category") {
                textfield(_category)
            }
            field("Number") {
                textfield(_number)
            }
            field("Price") {
                textfield(_price)
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    val productId = _id.value.toLongOrNull()
                    productId?.let {
                        productUIController.updateProduct(
                            _name.value,
                            _category.value,
                            _number.value,
                            _price.value
                        )
                        // 显示更新成功的提示
                    } ?: run {
                        // 显示错误消息，例如ID无效
                    }
                }
            }
            button("Cancel") {
                action {
                    // 关闭当前视图或返回上一个视图
                    productUIController.closeUpdate()
                }
            }
        }
    }

    // 当视图显示时，如果有有效的产品ID，则加载要更新的产品信息
    override fun onDock() {
        val productId = _id.value.toLongOrNull()
        productId?.let {
            val productToUpdate = productUIController.productStore.findOne(it)
            productToUpdate?.apply {
                _name.value = name
                _category.value = category
                _number.value = number
                _price.value = price
            }
        }
    }
}


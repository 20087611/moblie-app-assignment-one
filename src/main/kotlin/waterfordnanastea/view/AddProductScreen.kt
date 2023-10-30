import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*
import waterfordnanastea.controller.ProductUIController
import waterfordnanastea.modles.Product

class AddProductScreen : View("Add Product") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _category = model.bind { SimpleStringProperty() }
    val _number = model.bind { SimpleIntegerProperty() }
    val _price = model.bind { SimpleIntegerProperty() }
    val ProductUIController: ProductUIController by inject()

    override val root = form {
        setPrefSize(600.0, 300.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Category") {
                textfield(_category).required()
            }
            field("Number") {
                textfield(_number).required()
            }
            field("Price") {
                textfield(_price).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        ProductUIController.addProduct(_name.toString(),_category.toString(),_name.value.toInt(),_price.value.toInt())
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    ProductUIController.closeAdd()
                }
            }
        }
    }

    override fun onDock() {
        _name.value = ""
        _category.value = ""
        _number.value = 0
        _price.value = 0
        model.clearDecorators()
    }
}

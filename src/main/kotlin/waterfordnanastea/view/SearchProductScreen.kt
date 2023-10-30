package waterfordnanastea.view

import javafx.beans.property.SimpleStringProperty
import waterfordnanastea.modles.Product
import tornadofx.*
import waterfordnanastea.controller.ProductUIController

class SearchProductScreen : View("Search Products") {
    private val productUIController: ProductUIController by inject()
    private val searchTerm = SimpleStringProperty()

    override val root = vbox {
        setPrefSize(600.0, 200.0)
        form {
            fieldset("Search Product") {
                field("Search Term") {
                    textfield(searchTerm)
                }
                button("Search") {
                    action {
                        val searchResults = productUIController.searchProducts(searchTerm.value).observable()
                        find<SearchResultsFragment>(mapOf("searchResults" to searchResults)).openModal()
                    }
                }
            }
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    productUIController.closeSearch()
                }
            }
        }
    }
}

class SearchResultsFragment : Fragment() {
    val searchResults: List<Product> by param()

    override val root = vbox {
        tableview(searchResults.observable()) {
            readonlyColumn("ID", Product::id)
            readonlyColumn("Name", Product::name)
            readonlyColumn("Category", Product::category)
            readonlyColumn("Number", Product::number)
            readonlyColumn("Price", Product::price)
        }
    }
}

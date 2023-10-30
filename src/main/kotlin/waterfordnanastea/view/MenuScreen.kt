package waterfordnanastea.view

import javafx.application.Platform
import javafx.geometry.Orientation
import tornadofx.*
import waterfordnanastea.controller.ProductUIController

class MenuScreen : View("NanasTea Shop Main Menu") {
    private val productUIController: ProductUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Product") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    // 这里添加逻辑以加载添加产品的视图
                    productUIController.loadAddScreen()
                }
            }
            text("")
            button("Update Product") {
                useMaxWidth = true
                action {
                    // 这里添加逻辑以加载更新产品的视图
                    productUIController.loadUpdateScreen()
                }
            }
            text("")
            button("List Products") {
                useMaxWidth = true
                action {
                    // 这里添加逻辑以加载列出所有产品的视图
                    productUIController.loadListScreen()
                }
            }
            text("")
            button("Search Product") {
                useMaxWidth = true
                action {
                    // 这里添加逻辑以加载搜索产品的视图
                    productUIController.loadSearchScreen()
                }
            }
            text("")
            button("Delete Product") {
                useMaxWidth = true
                action {
                    // 这里添加逻辑以加载删除产品的视图
                    productUIController.loadDeleteScreen()
                }
            }
            text("")
            button("Exit") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    Platform.exit()
                    System.exit(0)
                }
            }
        }
    }
}


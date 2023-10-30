package waterfordnanastea.view

import waterfordnanastea.modles.Product
import waterfordnanastea.modles.ProductJSONStore

class ProductView {
    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Product")
        println(" 2. Update Product")
        println(" 3. List All Product")
        println(" 4. Search Product")
        println(" 5. Delete Product")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readln()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun addProductData(products: Product) : Boolean {

        println()
        print("Enter a Name : ")
        products.name = readln()!!
        print("Enter a Category : ")
        products.category = readln()!!
        print("Enter an Amount : ")
        products.number = readln().toInt()!!
        print("Enter an Price : ")
        products.price = readln().toInt()!!

        return products.name.isNotEmpty() && products.category.isNotEmpty() &&
                products.number != null && products.price != null
    }

    fun updateProduct(products: Product) : Boolean {

        var tempName: String?
        var tempCategory: String?
        var tempAmount: Int?
        var tempPrice: Int?

        if (products != null) {
            print("Enter a new Name for [ " + products.name + " ] : ")
            tempName = readln()!!
            print("Enter a new Category for [ " + products.category + " ] : ")
            tempCategory = readln()!!
            print("Enter a new Amount for [ " + products.number + " ] : ")
            tempAmount = readln().toInt()!!
            print("Enter a new Price for [ " + products.price + " ] : ")
            tempPrice = readln().toInt()!!

            if (!tempName.isNullOrEmpty() && !tempCategory.isNullOrEmpty()) {
                products.name = tempName
                products.category = tempCategory
                products.number = tempAmount
                products.price = tempPrice
                return true
            }
        }
        return false
    }


    fun listProducts(products: ProductJSONStore) {
        println("List All Products")
        println()
        products.logAll()
        println()
    }

    fun showProducts(products: Product) {
        if(products != null)
            println("Product Details [ $products ]")
        else
            println("Product Not Found...")
    }



    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readln()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}
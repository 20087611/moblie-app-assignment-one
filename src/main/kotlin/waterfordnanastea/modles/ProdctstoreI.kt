package waterfordnanastea.modles

interface ProdctstoreI {
    fun findAll(): List<Product>
    fun findOne(id: Long): Product?
    fun create(product: Product)
    fun update(product: Product)
    fun delete(product: Product)
}
package waterfordnanastea.modles

/**
 * 数据模型类，代表一个产品。
 *
 * @property name 产品的名称。
 * @property category 产品所属的类别。
 * @property number 产品的数量或库存。
 * @property price 产品的价格。
 */
data class Product(
    var id: Long = 0,
    var name: String = "",       // 产品名称
    var category: String = "",   // 产品类别
    var number: Int = 0,         // 产品数量或库存
    var price: Int = 0          // 产品价格
)


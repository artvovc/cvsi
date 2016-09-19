package android.cvsi.models.request

import android.cvsi.enums.CategoryEnum
import android.cvsi.enums.CurrencyEnum
import java.io.Serializable
import java.util.*

class ProductSearchClientRequest : Serializable {
    var title: String? = null
    var currency: CurrencyEnum? = null
    var minPrice: Long? = null
    var maxPrice: Long? = null
    var categories: Set<CategoryEnum>? = null
    var minCreatedDate: Long? = null
    var maxCreatedDate: Long? = null
    var offset: Long? = null
    var count: Long? = null
    var myProducts: Boolean? = null
    var orderByPrice: Boolean? = null
    var orderByCreatedDate: Boolean? = null

    init {
        this.minPrice = null//0L;
        this.maxPrice = java.lang.Long.MAX_VALUE
        this.minCreatedDate = Date().time - 1000L * 60 * 60 * 24 * 30// 30 days earlier
        this.maxCreatedDate = Date().time
        this.offset = 0L
        this.count = 50L
        this.myProducts = false
        this.orderByPrice = false
        this.orderByCreatedDate = false
    }
}

package android.cvsi.models.response

import android.cvsi.enums.CategoryEnum
import android.cvsi.enums.CurrencyEnum
import java.io.Serializable

class ProductTemplateResponse : Serializable {
    var id: Long? = null
    var title: String? = null
    var description: String? = null
    var currency: CurrencyEnum? = null
    var price: Long? = null
    var borrow: Boolean? = null
    var limitDate: Long? = null
    var categories: Set<CategoryEnum>? = null
    var userName: String? = null
    var createdDate: Long? = null
    var updatedDate: Long? = null
}

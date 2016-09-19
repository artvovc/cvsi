package android.cvsi.models.request

import java.io.Serializable

class UserUpdateClientRequest : Serializable {
    var username: String? = null
    var name: String? = null
    var surname: String? = null
    var phone: String? = null
    var password: String? = null
}

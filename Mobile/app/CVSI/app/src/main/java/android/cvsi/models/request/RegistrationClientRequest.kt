package android.cvsi.models.request

import java.io.Serializable

class RegistrationClientRequest() : Serializable {
    var email: String? = null
    var password: String? = null
    var phone: String? = null
    var username: String? = null
    var name: String? = null
    var surname: String? = null
    var createdDate: Long? = null

}

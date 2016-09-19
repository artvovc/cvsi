package android.cvsi.models.request

import java.io.Serializable

class MessageSaveClientRequest : Serializable {
    var message: String? = null
    var createdDate: Long? = null
    var username: String? = null
}

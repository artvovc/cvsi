package android.cvsi.models

import android.cvsi.models.error.ServerResponseStatus
import java.io.Serializable

class MessageDto : ServerResponseStatus(), Serializable {
    var id: Long? = null
    var message: String? = null
    var createdDate: Long? = null

    var username: String? = null
}

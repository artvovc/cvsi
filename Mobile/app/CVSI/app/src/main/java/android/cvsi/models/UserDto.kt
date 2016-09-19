package android.cvsi.models

import android.cvsi.enums.ErrorEnum
import android.cvsi.models.error.ServerResponseStatus

import java.io.Serializable

class UserDto : ServerResponseStatus(), Serializable {
    var id: Long? = null
    var username: String? = null
    var name: String? = null
    var surname: String? = null
    var phone: String? = null
    var email: String? = null
}

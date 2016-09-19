package android.cvsi.models

import android.cvsi.enums.ErrorEnum
import android.cvsi.models.error.ServerResponseStatus
import java.io.Serializable

class RegistrationDto : ServerResponseStatus(), Serializable {
    var token: String? = null
    var userDto: UserDto? = null
}

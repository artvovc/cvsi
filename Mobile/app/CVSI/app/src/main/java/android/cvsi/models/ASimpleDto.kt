package android.cvsi.models


import android.cvsi.models.error.ServerResponseStatus

class ASimpleDto : ServerResponseStatus() {
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: Int = 0
    var createTime: Long? = null
}

package android.cvsi.models.error


import android.cvsi.enums.ErrorEnum
import java.io.Serializable

open class ServerResponseStatus : Serializable {
    var error: ErrorEnum? = null
    var status: String? = null

    constructor() {
        status = "OK"
    }

    constructor(error: ErrorEnum, status: String) {
        this.error = error
        this.status = status
    }

    constructor(serverResponseStatus: ServerResponseStatus) {
        this.error = serverResponseStatus.error
        this.status = serverResponseStatus.status
    }

}

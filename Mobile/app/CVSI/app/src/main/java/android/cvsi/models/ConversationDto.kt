package android.cvsi.models


import android.cvsi.models.error.ServerResponseStatus

import java.io.Serializable

class ConversationDto : ServerResponseStatus(), Serializable {
    var id: Long? = null
    var receptorUsername: String? = null
    var title: String? = null
    var notReadMessages: Long? = null
    var createdDate: Long? = null

    init {
        notReadMessages = 0L
    }
}

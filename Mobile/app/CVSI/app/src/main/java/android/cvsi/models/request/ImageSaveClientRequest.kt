package android.cvsi.models.request

import java.io.Serializable

class ImageSaveClientRequest : Serializable {
    var imageType: String? = null
    var image: String? = null
    var createdDate: Long? = null
    var productId: Long? = null
}

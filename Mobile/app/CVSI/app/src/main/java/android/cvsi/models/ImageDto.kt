package android.cvsi.models

import android.cvsi.models.error.ServerResponseStatus
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser
import java.io.Serializable

class ImageDto : ServerResponseStatus(), Serializable {
    var imageType: ImageHeaderParser.ImageType? = null
    var image: String? = null
    var createdDate: Long? = null
}

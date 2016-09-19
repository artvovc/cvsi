package android.cvsi.models

import android.cvsi.enums.ErrorEnum
import android.cvsi.models.error.ServerResponseStatus
import android.cvsi.models.response.ProductTemplateResponse
import java.io.Serializable

class ProductDto : ServerResponseStatus, Serializable {
    var productTemplateResponse: ProductTemplateResponse? = null

    constructor() {
    }

    constructor(error: ErrorEnum, status: String, productTemplateResponse: ProductTemplateResponse) : super(error, status) {
        this.productTemplateResponse = productTemplateResponse
    }

    constructor(serverResponseStatus: ServerResponseStatus, productTemplateResponse: ProductTemplateResponse) : super(serverResponseStatus) {
        this.productTemplateResponse = productTemplateResponse
    }

}

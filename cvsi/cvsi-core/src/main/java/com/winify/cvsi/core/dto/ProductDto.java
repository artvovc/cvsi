package com.winify.cvsi.core.dto;

import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;
import com.winify.cvsi.core.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "ProductDto", description = "contain product minimal necessary information")
public class ProductDto extends ServerResponseStatus implements Serializable {
    @ApiModelProperty(name = "productTemplateResponse", dataType = "ProductTemplateResponse", required = true, notes = "contain product information")
    private ProductTemplateResponse productTemplateResponse;

    public ProductDto() {
    }

    public ProductDto(ErrorEnum error, String status, ProductTemplateResponse productTemplateResponse) {
        super(error, status);
        this.productTemplateResponse = productTemplateResponse;
    }

    public ProductDto(ServerResponseStatus serverResponseStatus, ProductTemplateResponse productTemplateResponse) {
        super(serverResponseStatus);
        this.productTemplateResponse = productTemplateResponse;
    }

    public ProductTemplateResponse getProductTemplateResponse() {
        return productTemplateResponse;
    }

    public void setProductTemplateResponse(ProductTemplateResponse productTemplateResponse) {
        this.productTemplateResponse = productTemplateResponse;
    }

    public ProductDto setServerResponseStatus (ErrorEnum errorEnum, String status){
        this.setError(errorEnum);
        this.setStatus(status);
        return this;
    }
}

package com.winify.cvsi.server.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.winify.cvsi.core.dto.ImageDto;
import com.winify.cvsi.core.dto.ListDto;
import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.dto.comparator.PriceComparator;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.ImageUploadClientRequest;
import com.winify.cvsi.core.dto.templates.request.ProductCreateClientRequest;
import com.winify.cvsi.core.dto.templates.request.ProductSearchClientRequest;
import com.winify.cvsi.core.dto.templates.request.ProductUpdateClientRequest;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.enums.ImageType;
import com.winify.cvsi.server.facade.ImageFacade;
import com.winify.cvsi.server.facade.ProductFacade;
import com.winify.cvsi.server.facade.UserFacade;
import com.winify.cvsi.server.security.userdetail.CustomUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@Api
@RequestMapping(
        name = "product controller",
        path = "/product",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProductController {
    private final ProductFacade productFacade;
    private final UserFacade userFacade;
    private final ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(ProductController.class);

    @Autowired
    public ProductController(UserFacade userFacade, ProductFacade productFacade, ImageFacade imageFacade) {
        this.userFacade = userFacade;
        this.productFacade = productFacade;
        this.imageFacade = imageFacade;
    }

    @PostMapping(
            path = "/{productId}/image",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public HttpEntity<ServerResponseStatus> postImages(
            @ApiParam(
                    name = "productId",
                    required = true
            )
            @PathVariable Long productId,
            @ModelAttribute("defaultImageName") String defaultImageName,
            @ModelAttribute("files") ImageUploadClientRequest files

    ) {
        files.getFiles().forEach(multipartFile -> {
            String imageOriginalName = multipartFile.getOriginalFilename();
            String id = imageFacade.saveImage(multipartFile, imageOriginalName, multipartFile.getContentType());
            Product product = productFacade.getProductById(productId);
            imageFacade.saveImage(
                    product,
                    id,
                    Objects.equals(imageOriginalName, defaultImageName) ? ImageType.DEFAULT_IMAGE : ImageType.ANOTHER_IMAGE
            );
        });
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping(
            path = "/{productId}/image",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpEntity<ListDto<ImageDto>> getImages(
            @PathVariable("productId") Long productId
    ) {
        ListDto<ImageDto> imageIds;
        imageIds = imageFacade.getImages(productId);
        imageIds.setError(ErrorEnum.SUCCESS);
        imageIds.setStatus("OK");
        return new ResponseEntity<>(imageIds, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{productId}/image/default",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpEntity<byte[]> getDefaultImage(
            @PathVariable("productId") Long productId
    ) {
        GridFSDBFile imagefile = imageFacade.getImage(imageFacade.getDefaultImage(productId).getImage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imagefile.getContentType()));
        headers.setContentLength(imagefile.getLength());
        byte[] image = new byte[0];
        try {
            image = IOUtils.toByteArray(imagefile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "postProduct",
            notes = "save new product into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = ProductDto.class,
            nickname = "postProduct"
    )
    public HttpEntity<ProductDto> postProduct(
            @ApiParam(
                    name = "productCreateClientRequest",
                    required = true,
                    value = "create product model"
            )
            @RequestBody @Valid ProductCreateClientRequest productCreateClientRequest
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new ProductBuilder().getProduct(productCreateClientRequest);
        product.setUser(userFacade.getUser(user.getId()));
        Long productId = productFacade.saveProduct(product);
        ProductDto productDto = productFacade.getProductDtoById(productId);
        productDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private HttpEntity<ListDto<ProductTemplateResponse>> getProduct(
            @ModelAttribute @Valid ProductSearchClientRequest productSearchClientRequest
    ) {
        ListDto<ProductTemplateResponse> productListDto = new ListDto<>();
        if (productSearchClientRequest.getMyProducts()) {
            CustomUserDetails user = new CustomUserDetails();
            try {
                user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } catch (Exception exp) {
                productListDto.setStatus(exp.getMessage());
            } finally {
                productListDto.setList(productFacade.getMyProducts(user.getId())).sortBy(new PriceComparator());
            }
        } else
            productListDto.setList(productFacade.getProducts(productSearchClientRequest)).sortBy(new PriceComparator());
        productListDto.setError(ErrorEnum.SUCCESS);
        productListDto.setStatus("OK");
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(
            value = "putProduct",
            notes = "update product from database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "PUT",
            response = ProductDto.class,
            nickname = "putProduct"
    )
    public HttpEntity<ProductDto> putProduct(
            @ApiParam(
                    name = "productUpdateClientRequest",
                    required = true,
                    value = "update product model"
            )
            @RequestBody @Valid ProductUpdateClientRequest productUpdateClientRequest
    ) {
        productFacade.updateProduct(productFacade.getProductById(productUpdateClientRequest.getProductId()), productUpdateClientRequest);
        ProductDto productDto = productFacade.getProductDtoById(productUpdateClientRequest.getProductId());
        productDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productId}")
    @ApiOperation(
            value = "deleteProduct",
            notes = "delete product from database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "DELETE",
            response = ServerResponseStatus.class,
            nickname = "deleteProduct"
    )
    public HttpEntity<ServerResponseStatus> deleteProduct(
            @ApiParam(
                    name = "productId",
                    required = true,
                    example = "0"
            )
            @PathVariable("productId") @Valid Long productId
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long rowChanges = productFacade.updateProduct(user.getId(), productId);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

}

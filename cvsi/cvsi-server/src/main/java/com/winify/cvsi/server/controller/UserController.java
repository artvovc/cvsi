package com.winify.cvsi.server.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.winify.cvsi.core.dto.UserDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import com.winify.cvsi.core.dto.templates.request.UserUpdateClientRequest;
import com.winify.cvsi.core.dto.validator.RegistrationValidator;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Registration;
import com.winify.cvsi.db.model.User;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@Api(
        value = "/user",
        description = "services for users",
        produces = "application/json"
)
@RequestMapping(
        path = "/user",
        name = "user controller",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    private final UserFacade userFacade;
    private final ProductFacade productFacade;
    private final ImageFacade imageFacade;
    private final static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserFacade userFacade, ProductFacade productFacade, ImageFacade imageFacade) {
        this.userFacade = userFacade;
        this.productFacade = productFacade;
        this.imageFacade = imageFacade;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "postUserRegistrationData",
            notes = "save new user registration data into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "POST",
            response = ServerResponseStatus.class,
            nickname = "postUserRegistrationData"
    )
    public HttpEntity<ServerResponseStatus> postUserRegistrationData(
            @ApiParam(
                    name = "registrationClientRequest",
                    required = true,
                    value = "registration model"
            )
            @RequestBody @Valid RegistrationClientRequest registrationClientRequest
    ) {
        RegistrationValidator registrationValidator = new RegistrationValidator();
        if (registrationValidator.isValid(registrationClientRequest))
            try {

                userFacade.saveUserRegistrationData(registrationClientRequest);
                userFacade.sendMail(registrationClientRequest);

            } catch (Exception exp) {
                registrationValidator.setMessage(exp.getMessage());
            }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{hash}"
    )
    @ApiOperation(
            value = "postUser",
            notes = "save new user registration data into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "GET",
            response = ServerResponseStatus.class,
            nickname = "postUser"
    )
    public HttpEntity<ServerResponseStatus> postUser(
            @ApiParam(
                    name = "hash",
                    required = true,
                    value = "hash"
            )
            @PathVariable String hash
    ) {
        Registration registration = userFacade.getRegistrationDataByHash(hash);
        if (registration != null) {
            userFacade.saveUser(registration);
            userFacade.deleteUserRegistrationData(registration);
        }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @PostMapping(
            path = "/image",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(
            value = "postUserImage",
            notes = "save new user image into database",
            produces = "application/json",
            consumes = "multipart/form-data",
            httpMethod = "POST",
            response = ServerResponseStatus.class,
            nickname = "postUserImage"
    )
    public HttpEntity<ServerResponseStatus> postImage(
            @ModelAttribute("file") MultipartFile file,
            HttpServletRequest request
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = imageFacade.saveImage(file, file.getOriginalFilename(), file.getContentType());
        userFacade.updateUser(userFacade.getUser(user.getId()), id);
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(
            value = "getUser",
            notes = "get user info by token",
            produces = "application/json",
            httpMethod = "GET",
            response = UserDto.class,
            nickname = "getUser"
    )
    public HttpEntity<UserDto> getUser(
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userFacade.getUserDto(user);
        userDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/image")
    @ApiOperation(
            value = "getUserImage",
            notes = "get user image from database",
            httpMethod = "GET",
            response = ServerResponseStatus.class,
            nickname = "getUserImage"
    )
    public HttpEntity<byte[]> getImage(
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GridFSDBFile imagefile = imageFacade.getImage(user.getImage());
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

    @PutMapping
    @ApiOperation(
            value = "updateUser",
            notes = "update user into database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "PUT",
            response = UserDto.class,
            nickname = "updateUser"
    )
    public HttpEntity<UserDto> updateUser(
            @ApiParam(
                    name = "userUpdateClientRequest",
                    value = "update user model"
            )
            @RequestBody @Valid UserUpdateClientRequest userUpdateClientRequest
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        userFacade.updateUser(userTemp, userUpdateClientRequest);
        UserDto userDto = userFacade.getUserDto(user.getId());
        userDto.setServerResponseStatus(ErrorEnum.SUCCESS, "OK");
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(
            value = "deleteUser",
            notes = "delete user from database",
            produces = "application/json",
            consumes = "application/json",
            httpMethod = "DELETE",
            response = ServerResponseStatus.class,
            nickname = "deleteUser"
    )
    public HttpEntity<ServerResponseStatus> deleteUser(
    ) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = userFacade.getUser(user.getId());
        if (userTemp != null) {
            userTemp.setArchived(true);
            productFacade.getMyProductsToArchivate(user.getId()).forEach(productFacade::updateProductToArchivate);
            userFacade.updateUser(userTemp);
        }
        return new ResponseEntity<>(new ServerResponseStatus(ErrorEnum.SUCCESS, "OK"), HttpStatus.OK);
    }
}


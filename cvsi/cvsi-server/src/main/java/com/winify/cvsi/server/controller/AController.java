package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.server.facade.AFacade;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "Do not use this controller, because his destination is only for study how it works")
@RequestMapping(path = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class AController {
    @Autowired
    private AFacade aFacade;
    final static Logger log = Logger.getLogger(AController.class);

    @GetMapping(path = "/me/{modelId}")
    public HttpEntity<ASimpleDto> getAModel(@PathVariable Long modelId){
        log.info("get by id try to get something");

        return new ResponseEntity(aFacade.getAModel(modelId), HttpStatus.OK);
    }

    @PostMapping (path = "/me")
    public HttpEntity<ServerResponseStatus> saveAModel(@RequestBody ASimpleDto userdto ){
        aFacade.saveAModel(userdto);

        return new ResponseEntity(new ServerResponseStatus(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/him")
    public HttpEntity<ServerResponseStatus> getHim(){
        log.info("da ladnoooo");

        return new ResponseEntity(new ServerResponseStatus(ErrorEnum.UNKNOWN_ERROR, "NOK"), HttpStatus.OK);
    }
}
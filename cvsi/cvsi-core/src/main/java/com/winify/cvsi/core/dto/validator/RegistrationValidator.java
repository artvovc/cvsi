package com.winify.cvsi.core.dto.validator;

import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;

public class RegistrationValidator extends Validator<RegistrationClientRequest>{

    public RegistrationValidator(){
        super();
    }

    public Boolean isValid(RegistrationClientRequest request) {

        return true;
    }
}

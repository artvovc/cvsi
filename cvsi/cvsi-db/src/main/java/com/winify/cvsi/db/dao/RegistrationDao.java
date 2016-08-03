package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Registration;

public interface RegistrationDao extends CrudOperations<Registration, Long> {

    Registration findByHash(String hash);
}

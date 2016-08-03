package com.winify.cvsi.core.service;

import com.winify.cvsi.db.dao.RegistrationDao;
import com.winify.cvsi.db.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final RegistrationDao registrationDao;

    @Autowired
    public RegistrationService(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Transactional
    public Long saveRegistrationData(Registration registration){
        return this.registrationDao.save(registration);
    }

    @Transactional
    public Registration getRegistrationDataByHash(String hash){
        return this.registrationDao.findByHash(hash);
    }

    @Transactional
    public void deleteUserRegistrationData(Registration registration) {
        this.registrationDao.delete(registration);
    }
}

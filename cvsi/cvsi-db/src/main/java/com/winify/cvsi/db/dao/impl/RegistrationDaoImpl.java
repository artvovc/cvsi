package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.RegistrationDao;
import com.winify.cvsi.db.model.Registration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RegistrationDaoImpl extends AbstractDao<Registration, Long> implements RegistrationDao {

    public RegistrationDaoImpl() {
        super(Registration.class);
    }

    @Override
    public Registration findByHash(String hash) {
        return this.getCurrentSession()
                .createQuery("SELECT r FROM Registration AS r WHERE r.hash = :pHash",clazz)
                .setParameter("pHash",hash)
                .getSingleResult();
    }
}

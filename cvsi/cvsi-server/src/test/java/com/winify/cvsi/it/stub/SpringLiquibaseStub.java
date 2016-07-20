package com.winify.cvsi.it.stub;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

public class SpringLiquibaseStub extends SpringLiquibase {
    @Override
    public void afterPropertiesSet() throws LiquibaseException {
    }
}

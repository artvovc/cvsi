package com.winify.cvsi.it.stub;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

/**
 * Created by kuk on 7/6/16.
 */
public class SpringLiquibaseStub extends SpringLiquibase {
    @Override
    public void afterPropertiesSet() throws LiquibaseException {
    }
}

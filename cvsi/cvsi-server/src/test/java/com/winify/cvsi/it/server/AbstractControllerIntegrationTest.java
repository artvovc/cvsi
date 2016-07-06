package com.winify.cvsi.it.server;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.winify.cvsi.dbunit.loader.CustomFlatXmlDataSetLoader;
import org.junit.runner.RunWith;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:integrationTest-appContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = CustomFlatXmlDataSetLoader.class)
public class AbstractControllerIntegrationTest {
}

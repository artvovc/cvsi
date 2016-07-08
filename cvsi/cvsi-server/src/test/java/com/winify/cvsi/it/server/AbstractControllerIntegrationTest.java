package com.winify.cvsi.it.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.winify.cvsi.dbunit.loader.CustomFlatXmlDataSetLoader;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;

import static com.winify.cvsi.core.utils.JsonUtils.convertJsonSourceToGenericObject;
import static com.winify.cvsi.core.utils.JsonUtils.convertJsonSourceToObject;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:integrationTest-appContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = CustomFlatXmlDataSetLoader.class)
public class AbstractControllerIntegrationTest {
    @Autowired
    public WebApplicationContext wac;

    @Resource
    private FilterChainProxy springSecurityFilter;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilter)
                .apply(springSecurity(springSecurityFilter))
                .build();
    }

    @SuppressWarnings("unchecked")
    public <T> T mvcResultToResponse(MvcResult mvcResult, Class<T> clazz) throws IOException {
        return (T) convertJsonSourceToObject(mvcResult.getResponse().getContentAsString(), clazz);
    }

    @SuppressWarnings("unchecked")
    public <T> T mvcResultToGenericResponse(MvcResult mvcResult, TypeReference<T> ref) throws IOException {
        return convertJsonSourceToGenericObject(mvcResult.getResponse().getContentAsString(), ref);
    }



}

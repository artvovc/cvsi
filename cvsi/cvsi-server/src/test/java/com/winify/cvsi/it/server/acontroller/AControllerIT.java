package com.winify.cvsi.it.server.acontroller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.it.server.AbstractControllerIntegrationTest;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DatabaseTearDown("classpath:/testdata/com/winify/cvsi/it/server/tear-down-common.xml")
public class AControllerIT extends AbstractControllerIntegrationTest {

    @Test
    @DatabaseSetup("classpath:/testdata/com/winify/cvsi/it/server/db-setup-common.xml")
    public void test() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/rest/me/2")
                .contentType(APPLICATION_JSON)
//                .with(testSecurityContext())
        ).andExpect(status().isOk())
                .andReturn();

        ASimpleDto aSimpleDto = mvcResultToResponse(mvcResult, ASimpleDto.class);
        assertThat(aSimpleDto, notNullValue());
        assertThat(aSimpleDto.getId(), notNullValue());

    }
}

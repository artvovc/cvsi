package com.winify.cvsi.it.server.acontroller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.winify.cvsi.core.dto.ASimpleDto;
import com.winify.cvsi.core.dto.error.ServerResponseStatus;
import com.winify.cvsi.core.utils.JsonUtils;
import com.winify.cvsi.it.server.AbstractControllerIntegrationTest;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("alexei.popa@example.com")
@DatabaseTearDown("classpath:/testdata/com/winify/cvsi/it/server/tear-down-common.xml")
public class AControllerIT extends AbstractControllerIntegrationTest {

    @Test
    @DatabaseSetup("classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_models.xml")
    @ExpectedDatabase(value ="classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_models.xml", assertionMode = NON_STRICT)
    public void getAModel() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/rest/me/2")
                .contentType(APPLICATION_JSON)
                .with(testSecurityContext())
        ).andExpect(status().isOk())
                .andReturn();

        ASimpleDto aSimpleDto = mvcResultToResponse(mvcResult, ASimpleDto.class);
        assertThat(aSimpleDto, notNullValue());
        assertThat(aSimpleDto.getId(), equalTo(2L));
        assertThat(aSimpleDto.getAge(), equalTo(22));
        assertThat(aSimpleDto.getFirstName(), equalTo("Andrei"));
        assertThat(aSimpleDto.getLastName(), equalTo("Voinicul"));
        assertThat(aSimpleDto.getStatus(), equalTo("OK"));
        assertThat(aSimpleDto.getError(), nullValue());

    }

    @Test
    @DatabaseSetup("classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_models.xml")
    @ExpectedDatabase(value ="classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_model_added.xml", assertionMode = NON_STRICT)//, columnFilters = {"create"}
    public void saveAModel() throws Exception {

        ASimpleDto aSimpleDto = new ASimpleDto();
        aSimpleDto.setAge(23);
        aSimpleDto.setLastName("Nebunu");
        aSimpleDto.setFirstName("Luca");

        MvcResult mvcResult = mockMvc.perform(post("/rest/me/")
                        .contentType(APPLICATION_JSON)
                .content(JsonUtils.convertObjectToJsonBytes(aSimpleDto))
                .with(testSecurityContext())
        ).andExpect(status().isOk())
                .andReturn();

        ServerResponseStatus responseDto = mvcResultToResponse(mvcResult, ServerResponseStatus.class);
        assertThat(responseDto, notNullValue());
        assertThat(responseDto.getStatus(), equalTo("OK"));
        assertThat(responseDto.getError(), nullValue());

    }

    @Test
    @DatabaseSetup("classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_models.xml")
    @ExpectedDatabase(value ="classpath:/testdata/com/winify/cvsi/it/server/AControllerIT/a_models.xml", assertionMode = NON_STRICT)
    public void aAdminRequest_forbidden() throws Exception {

        mockMvc.perform(get("/rest/admin/")
                .contentType(APPLICATION_JSON)
                .with(testSecurityContext())
        ).andExpect(status().is(403))
                .andReturn();

    }
}

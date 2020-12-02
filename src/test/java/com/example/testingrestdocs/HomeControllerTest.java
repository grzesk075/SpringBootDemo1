package com.example.testingrestdocs;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
// Can be @WebMvcTest(HomeController.class) instead of @AutoConfigureMockMvc but without @SpringBootTest
@AutoConfigureRestDocs
class HomeControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeControllerTest.class);

    @Autowired
    private ApplicationContext appCtx;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldLoadContext() {
        //ApplicationContext test
        LOGGER.info(String.format("AppCtx: %s loaded.", appCtx));
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        //Web Layer test
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello world")))
                .andDo(MockMvcRestDocumentation.document("home"));
        // Creates Ascii Doctor snippets with request and response
    }
}
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    public void shouldServeIndex_mockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(contextPath + "/").contextPath(contextPath))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldServeIndex_webClient() throws Exception {
        try (final var webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).contextPath(contextPath).build()) {
            webClient.getPage("http://localhost" + contextPath + "/");
        }
    }

}

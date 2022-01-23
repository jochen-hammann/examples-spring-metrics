package com.example.spring.metrics.gettingstarted.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloControllerIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getTest() throws Exception
    {
        mvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void postTest() throws Exception
    {
        Hello hello = new Hello("postTest: Hello, world!");

        String jsonStr = this.objectMapper.writeValueAsString(hello);

        mvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON).content(jsonStr)).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void getWithRestClientMetrics() throws Exception
    {
        // We have to use the RestTemplateBuilder to create a RestTemplate with auto-configured metrics (https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-http-clients).

        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<Hello> response = restTemplate.getForEntity("http://localhost:{port}/hello", Hello.class, this.port);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // The following sleep can be used to verify the metrics with VisualVM for example.
        Thread.sleep(60000);
    }
}
